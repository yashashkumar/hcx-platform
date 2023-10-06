import { Navigate, Route, Routes } from 'react-router-dom';
import OTP from './pages/Authentication/OTP';
import VerifyOTP from './pages/Authentication/VerifyOTP';
import DefaultLayout from './layout/DefaultLayout';
import { useEffect, useState } from 'react';
import Loader from './common/Loader';
import Home from './pages/Home/Home';
import NewClaim from './pages/NewClaimCycle/NewClaim';
import SignUp from './pages/Authentication/SignUp';
import CoverageEligibility from './pages/ViewCoverageEligibilityDetails/CoverageEligibility';
import InitiateNewClaimRequest from './pages/InitiateNewClaimRequest/InitiateNewClaimRequest';
import ViewClaimRequestDetails from './pages/ViewClaimRequestDetails/ViewClaimRequestDetails';
import CoverageEligibilityRequest from './pages/CoverageEligibilityRequest/CoverageEligibilityRequest';
import PreAuthRequest from './pages/InitiatePreAuthRequest/PreAuthRequest';
import RequestSuccess from './components/RequestSuccess';
import { ToastContainer } from 'react-toastify';
import Profile from './pages/Profile/Profile';
import SendBankDetails from './pages/SendBankDetails/SendBankDetails';
import Success from './pages/SendBankDetails/Success';
import CoverageEligibilitySuccessPage from './components/CoverageEligibilitySuccessPage';

const App = () => {
  const [loading, setLoading] = useState<boolean>(true);

  useEffect(() => {
    setTimeout(() => setLoading(false), 1000);
  }, []);

  return loading ? (
    <Loader />
  ) : (
    <>
      <Routes>
        <Route path="/" element={<Navigate to={'/beneficiary-app'} />} />
        <Route path="/beneficiary-app" element={<OTP />} />
        <Route path="/beneficiary-app/signup" element={<SignUp />} />
        <Route path="/beneficiary-app/verify-otp" element={<VerifyOTP />} />
        <Route element={<DefaultLayout />}>
          <Route path="/beneficiary-app/home" element={<Home />} />
          <Route path="/beneficiary-app/new-claim" element={<NewClaim />} />
          <Route
            path="/beneficiary-app/coverage-eligibility-request"
            element={<CoverageEligibilityRequest />}
          />
          <Route
            path="/beneficiary-app/coverage-eligibility"
            element={<CoverageEligibility />}
          />
          <Route
            path="/beneficiary-app/initiate-claim-request"
            element={<InitiateNewClaimRequest />}
          />
          <Route
            path="/beneficiary-app/initiate-preauth-request"
            element={<PreAuthRequest />}
          />
          <Route
            path="/beneficiary-app/view-active-request"
            element={<ViewClaimRequestDetails />}
          />
          <Route
            path="/beneficiary-app/coverage-eligibility-success-page"
            element={<CoverageEligibilitySuccessPage />}
          />
          <Route
            path="/beneficiary-app/request-success"
            element={<RequestSuccess />}
          />
          <Route path="/beneficiary-app/profile" element={<Profile />} />
          <Route
            path="/beneficiary-app/bank-details"
            element={<SendBankDetails />}
          />
          <Route path="/beneficiary-app/success" element={<Success />} />
        </Route>
      </Routes>
    </>
  );
};

export default App;
