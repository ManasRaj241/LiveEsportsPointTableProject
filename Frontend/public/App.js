import React from "react";
import ReactDOM from "react-dom/client";
import Tournament from "../src/components/Tournament";
import Home from "../src/components/Home";
import "tailwindcss/tailwind.css";
import { RouterProvider, createBrowserRouter } from "react-router-dom";
import SelectTeams from "../src/components/SelectTeams";
import GroupConfirmation from "../src/components/GroupConfirmation";

const AppLayout = () => {
  return (
    <div>
      <Home />
    </div>
  );
};

const appRouter = createBrowserRouter([
  {
    path: "/",
    element: <Home />,
  },
  {
    path: "/tournament",
    element: <Tournament />,
  },
  {
    path: "/SelectTeams/:tournamentId",
    element: <SelectTeams />,
  },
  {
    path: "/GroupConfirmation/:tournamentId/:maxGroups",
    element: <GroupConfirmation />,
  },
]);

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(<RouterProvider router={appRouter} />);
