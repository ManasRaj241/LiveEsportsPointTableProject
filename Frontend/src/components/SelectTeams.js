import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";

const SelectTeams = () => {
  const params = useParams();
  const tournamentId = params.tournamentId;
  const [tournamentData, setTournamentData] = useState({});
  const [teamsData, setTeamsData] = useState([]);
  const [currentTeamIndex, setCurrentTeamIndex] = useState(0);
  const [selectedGroupName, setSelectedGroupName] = useState("");
  const [noOfSelectedTeams, setNoOfSelectedTeams] = useState(0);

  useEffect(() => {
    fetch(`http://localhost:8181/tournament/${tournamentId}`)
      .then((response) => response.json())
      .then((data) => setTournamentData(data))
      .catch((error) =>
        console.error("Error fetching tournament data:", error)
      );

    fetch("http://localhost:8181/teams")
      .then((response) => response.json())
      .then((data) => setTeamsData(data))
      .catch((error) => console.error("Error fetching teams data:", error));
  }, [tournamentId]);

  const handleNext = () => {
    const currentTeam = teamsData[currentTeamIndex];
    const dataToSend = {
      tournament: tournamentData,
      team: currentTeam,
      groupName: selectedGroupName,
    };

    fetch("http://localhost:8181/groups", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(dataToSend),
    })
      .then((response) => response.json())
      .then(() => {
        setCurrentTeamIndex((prevIndex) => prevIndex + 1);
        setSelectedGroupName("");
      })
      .catch((error) => console.error("Error saving data:", error));
  };

  if (!tournamentData || !teamsData.length) {
    return <div className="text-center mt-8 text-gray-600">Loading...</div>;
  }

  const maxGroups = tournamentData.maxGroups;
  const groupsOptions = Array.from(
    { length: maxGroups },
    (_, index) => `Group${index + 1}`
  );
  groupsOptions.push("Not Playing This Tournament");

  const currentTeam = teamsData[currentTeamIndex];

  return (
    <div className="container mx-auto mt-8 p-4 bg-orange-100 m-auto">
      <div className="max-w-md mx-auto bg-white p-6 rounded-md shadow-md">
        <div className="mb-4">
          <div className="text-lg font-semibold mb-2">
            <div>
              <h1>Number Of Selected teams : </h1>
              {noOfSelectedTeams}
            </div>
            Sl number: {currentTeamIndex + 1}
          </div>
          <div className="mb-2">
            Team Logo:{" "}
            <img
              src={
                "../" + "/images/" + currentTeam.image.replace("images/", "")
              }
              alt={currentTeam.name}
            />
          </div>
          <div className="mb-2">Team Name: {currentTeam.name}</div>
          <div className="mb-2">
            Assign Groups:
            <select
              value={selectedGroupName}
              onChange={(e) => setSelectedGroupName(e.target.value)}
              className="ml-2 p-2 border border-gray-300 rounded-md"
            >
              <option key="default" value="Select from the below options">
                Select from the below options
              </option>
              {groupsOptions.map((option) => (
                <option key={option} value={option}>
                  {option}
                </option>
              ))}
            </select>
          </div>
        </div>
        <button
          onClick={() => {
            if (selectedGroupName !== "Not Playing This Tournament") {
              setNoOfSelectedTeams((prevNo) => prevNo + 1);
            }
            handleNext();
          }}
          disabled={noOfSelectedTeams >= tournamentData.maxTeams}
          className="bg-blue-500 text-white px-4 py-2 rounded-md disabled:opacity-50"
        >
          Next
        </button>

        {noOfSelectedTeams >= tournamentData.maxTeams && (
          <button className="bg-green-500 text-white px-4 py-2 rounded-md mt-4 ml-36">
            See Group Details
          </button>
        )}
      </div>
    </div>
  );
};

export default SelectTeams;
