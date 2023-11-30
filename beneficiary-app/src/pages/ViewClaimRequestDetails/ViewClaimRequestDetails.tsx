import React, { useEffect, useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import strings from '../../utils/strings';
import { generateToken, searchParticipant } from '../../services/hcxService';
import {
  createCommunicationOnRequest,
  generateOutgoingRequest,
  isInitiated,
} from '../../services/hcxMockService';
import { toast } from 'react-toastify';
import LoadingButton from '../../components/LoadingButton';
import * as _ from "lodash";

const ViewClaimRequestDetails = () => {
  const location = useLocation();
  const details = location.state;
  const navigate = useNavigate();

  const [token, setToken] = useState<string>('');

  const [providerName, setProviderName] = useState<string>('');
  const [payorName, setPayorName] = useState<string>('');

  const [initiated, setInitiated] = useState(false);

  const [OTP, setOTP] = useState<any>();
  const [docs, setSupportingDocs] = useState<any>({});

  const [preAuthAndClaimList, setpreauthOrClaimList] = useState<any>([]);

  const [refresh, setRefresh] = useState<any>(false);

  const [loading, setLoading] = useState<any>(false);

  const participantCodePayload = {
    filters: {
      participant_code: { eq: location.state?.participantCode },
    },
  };

  const payorCodePayload = {
    filters: {
      participant_code: { eq: location.state?.payorCode },
    },
  };

  const config = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  const sendInfo = {
    ...details,
    payor: payorName,
    providerName: providerName,
  };

  useEffect(() => {
    const search = async () => {
      try {
        const tokenResponse = await generateToken();
        if (tokenResponse.statusText === 'OK') {
          setToken(tokenResponse.data.access_token);
        }
      } catch (err) {
        console.log(err);
      }
    };
    search();
    getSupportingDocsFromList();
  }, []);

  useEffect(() => {
    try {
      // if (token !== undefined) {
      const search = async () => {
        const tokenResponse = await generateToken();
        // if (tokenResponse.statusText === 'OK') {
        //   setToken(tokenResponse.data.access_token);
        // }
        const response = await searchParticipant(
          participantCodePayload,
          {
            headers: {
              Authorization: `Bearer ${tokenResponse.data.access_token}`,
            }
          },
        );
        setProviderName(response.data?.participants[0].participant_name);
        const payorResponse = await searchParticipant(
          payorCodePayload,
          {
            headers: {
              Authorization: `Bearer ${tokenResponse.data.access_token}`,
            }
          },
        );
        setPayorName(payorResponse.data?.participants[0].participant_name);
      };
      search();
      // }
    } catch (err) {
      console.log(err);
    }
  }, [token]);

  const claimRequestDetails: any = [
    {
      key: 'Provider :',
      value: providerName || '',
    },
    {
      key: 'Participant code :',
      value: details?.participantCode || '',
    },
    {
      key: 'Treatment/Service type :',
      value: details?.serviceType || '',
    },
    {
      key: 'Payor name :',
      value: payorName || '',
    },
    {
      key: 'Insurance ID :',
      value: details?.insuranceId || '',
    },
  ];

  const treatmentDetails = [
    {
      key: 'Service type :',
      value: details?.serviceType || '',
    },
    {
      key: 'Bill amount :',
      value: `INR ${details?.billAmount || ''}`,
    },
  ];

  const getVerificationPayload = {
    type: 'otp_verification',
    request_id: details?.apiCallId,
  };

  const getVerification = async () => {
    try {
      setRefresh(true);
      let res = await isInitiated(getVerificationPayload);
      setRefresh(false);
      if (res.status === 200) {
        setInitiated(true);
      }
    } catch (err) {
      setRefresh(false);
      console.log(err);
      toast.error('Communication is not initiated.');
    }
  };

  const recipientCode = localStorage.getItem('recipientCode');
  const payload = {
    request_id: details?.apiCallId,
    mobile: localStorage.getItem('mobile'),
    otp_code: OTP,
    type: 'otp',
    participantCode: process.env.SEARCH_PARTICIPANT_USERNAME,
    password: process.env.SEARCH_PARTICIPANT_PASSWORD,
    recipientCode: recipientCode
  };

  const verifyOTP = async () => {
    try {
      setLoading(true);
      const res = await createCommunicationOnRequest(payload);
      setLoading(false);
      setInitiated(false);
      toast.success(res.data?.message);
      navigate('/bank-details', { state: sendInfo });
    } catch (err) {
      setLoading(false);
      toast.error('Enter valid OTP!');
      console.log(err);
    }
  };

  const preauthOrClaimListPayload = {
    workflow_id: details?.workflowId || '',
    app: "BSP"
  };

  const getSupportingDocsFromList = async () => {
    let response = await generateOutgoingRequest(
      'bsp/request/list',
      preauthOrClaimListPayload
    );
    const data = response.data?.entries;
    setpreauthOrClaimList(data);

    const claimAndPreauthEntries = data.filter(
      (entry: any) => entry.type === 'claim' || entry.type === 'preauth'
    );

    // Extract supporting documents from the filtered entries
    // const supportingDocumentsArray =
    //   _.map(claimAndPreauthEntries, (entry: any) => entry.supportingDocuments)
    //     .flat();
    console.log(claimAndPreauthEntries)

    setSupportingDocs(claimAndPreauthEntries?.supportingDocuments);
  };

  const hasClaimApproved = preAuthAndClaimList.some(
    (entry: any) => entry.type === 'claim' && entry.status === 'Approved'
  );

  return (
    <>
      <div className="relative mb-4 items-center justify-between">
        {hasClaimApproved ? (
          <span
            className={
              'dark:text-green border-green absolute right-0 mr-2 rounded bg-success px-2.5 py-0.5 text-xs font-medium text-gray'
            }
          >
            Approved
          </span>
        ) : (
          <></>
        )}
        <h2 className="sm:text-title-xl1 text-2xl font-semibold text-black dark:text-white">
          {strings.CLAIM_REQUEST_DETAILS}
        </h2>
        <span>{details?.workflowId}</span>
      </div>
      <div className="rounded-lg border border-stroke bg-white p-2 px-3 shadow-default dark:border-strokedark dark:bg-boxdark">
        <div>
          {_.map(claimRequestDetails, (ele: any, index: any) => {
            return (
              <div key={index} className="mb-2">
                <h2 className="text-bold text-base font-bold text-black dark:text-white">
                  {ele.key}
                </h2>
                <span className="text-base font-medium">{ele.value}</span>
              </div>
            );
          })}
        </div>
      </div>
      <div className="mt-3 rounded-lg border border-stroke bg-white px-3 pb-3 shadow-default dark:border-strokedark dark:bg-boxdark">
        <div className="flex items-center justify-between">
          <h2 className="sm:text-title-xl1 text-1xl mt-2 mb-2 font-semibold text-black dark:text-white">
            {strings.TREATMENT_AND_BILLING_DETAILS}
          </h2>
        </div>
        <div>
          {_.map(treatmentDetails, (ele: any) => {
            return (
              <div className="flex gap-2">
                <h2 className="text-bold text-base font-bold text-black dark:text-white">
                  {ele.key}
                </h2>
                <span className="text-base font-medium">{ele.value}</span>
              </div>
            );
          })}
        </div>
      </div>
      {_.isEmpty(docs) ? null : <>
        <div className="mt-3 rounded-lg border border-stroke bg-white px-3 pb-3 shadow-default dark:border-strokedark dark:bg-boxdark">
          <div className="flex items-center justify-between">
            <h2 className="sm:text-title-xl1 text-1xl mt-2 mb-2 font-semibold text-black dark:text-white">
              {strings.SUPPORTING_DOCS}
            </h2>
          </div>
          {/* <div className="flex flex-wrap gap-2">
            {_.map(docs, (ele: any, index: any) => {
              console.log(ele)
              const parts = ele.split('/');
              const fileName = parts[parts.length - 1];
              return (
                <div>
                  <a
                    href={ele}
                    download
                    className="flex flex-col w-50 shadow-sm border border-gray-300 hover:border-gray-400 rounded-md px-3 py-1 font-medium text-gray-700 hover:text-black"
                  >
                    <span className="text-center">{fileName}</span>
                    <img src={ele} alt="" />
                    <div className="flex items-center justify-center">
                      <ArrowDownTrayIcon className="h-5 w-5 flex-shrink-0 mr-2 text-indigo-400" />
                      <span>Download</span>
                    </div>
                  </a>
                </div>
              );
            })}
          </div> */}
        </div>
      </>}

      {!hasClaimApproved ? (
        <div
          onClick={() => getVerification()}
          className="align-center mt-4 flex w-20 justify-center rounded bg-primary py-1 font-medium text-gray disabled:cursor-not-allowed disabled:bg-secondary disabled:text-gray"
        >
          {!refresh ? (
            <span className="cursor-pointer">Refresh</span>
          ) : (
            <LoadingButton className="align-center flex w-20 justify-center rounded bg-primary font-medium text-gray disabled:cursor-not-allowed" />
          )}
        </div>
      ) : (
        <>
          <button
            onClick={(event: any) => {
              event.preventDefault();
              navigate('/home');
            }}
            type="submit"
            className="align-center mt-8 flex w-full justify-center rounded bg-primary py-3 font-medium text-gray"
          >
            Home
          </button>
        </>
      )}

      {initiated ? (
        <>
          <div className="flex items-center justify-between">
            <h2 className="sm:text-title-xl1 text-1xl mt-2 mb-4 font-semibold text-black dark:text-white">
              {strings.NEXT_STEP}
            </h2>
          </div>
          <div className="rounded-lg border border-stroke bg-white p-2 px-3 shadow-default dark:border-strokedark dark:bg-boxdark">
            <div>
              <h2 className="text-bold text-base font-bold text-black dark:text-white">
                {strings.POLICYHOLDER_CONSENT}
              </h2>
              <label className="font-small mb-2.5 block text-left text-black dark:text-white">
                {strings.ENTER_OTP_TO_VERIFY_CLAIM}
              </label>
            </div>
            <div>
              <div className="relative">
                <input
                  required
                  onChange={(e: any) => {
                    setOTP(e.target.value);
                  }}
                  type="number"
                  placeholder="OTP"
                  className={
                    'w-full rounded-lg border border-stroke bg-transparent py-4 pl-6 pr-10 outline-none focus:border-primary focus-visible:shadow-none dark:border-form-strokedark dark:bg-form-input dark:focus:border-primary'
                  }
                />
              </div>
            </div>
            <div className="mb-5">
              {loading ? (
                <LoadingButton className="align-center mt-4 flex w-full justify-center rounded bg-primary py-4 font-medium text-gray disabled:cursor-not-allowed" />
              ) : (
                <button
                  onClick={(event: any) => {
                    event.preventDefault();
                    verifyOTP();
                  }}
                  type="submit"
                  className="align-center mt-4 flex w-full justify-center rounded bg-primary py-4 font-medium text-gray"
                >
                  {strings.VERIFY_OTP_BUTTON}
                </button>
              )}
            </div>
          </div>
        </>
      ) : null}
    </>
  );
};

export default ViewClaimRequestDetails;
