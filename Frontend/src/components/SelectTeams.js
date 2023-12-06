import React from "react";
import { useParams } from "react-router-dom";

const SelectTeams = () => {
  const params = useParams();
  return (
    <div>
      <h1>SelectTeams</h1>
      <h3>MaxTeams:{params.maxTeams}</h3>
      <h3>MaxGroups:{params.maxGroups}</h3>
    </div>
  );
};

export default SelectTeams;
