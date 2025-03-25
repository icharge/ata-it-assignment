import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import "./App.css";
import InstrumentSearch from "./components/InstrumentSearch";
import Layout from "./components/Layout";

function App() {
  return (
    <BrowserRouter>
      <Layout>
        <Routes>
          <Route path="/">
            <Route
              index
              element={<Navigate to="instruments/MUTUAL_FUND" replace />}
            />
            <Route
              path="instruments/MUTUAL_FUND"
              element={<InstrumentSearch instrumentType="MUTUAL_FUND" />}
            />
            <Route
              path="instruments/FIXED_INCOME"
              element={<InstrumentSearch instrumentType="FIXED_INCOME" />}
            />
            <Route
              path="*"
              element={<div className="text-center py-20">Page not found</div>}
            />
          </Route>
        </Routes>
      </Layout>
    </BrowserRouter>
  );
}

export default App;
