import React from "react";
import { useParams } from "react-router-dom";

const SelectTeams = () => {
  const params = useParams();
  return (
    <div>
      <h1>SelectTeams</h1>
      <h1>MaxTeams:{params.maxTeams}</h1>
      <h1>MaxGroups:{params.maxGroups}</h1>
    </div>
  );
};

export default SelectTeams;
