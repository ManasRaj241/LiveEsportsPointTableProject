import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import _ from "lodash";

const GroupConfirmation = () => {
  const params = useParams();
  const tId = params.tournamentId;
  const [groups, setGroups] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch(`http://localhost:8181/groups/${tId}`);
        const data = await response.json();
        const groupedTeams = _.groupBy(data, "groupName");

        setGroups(groupedTeams);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchData();
  }, [tId]);

  return (
    <div className="container mx-auto my-8">
      <h2 className="text-2xl font-bold mb-4">Group Confirmation</h2>

      {Object.entries(groups).map(([groupName, teams]) => (
        <div key={groupName} className="mb-6">
          <h2 className="text-xl font-semibold mb-2">{groupName}</h2>
          <div className="grid grid-cols-3 gap-4">
            {teams.map((team) => (
              <div key={team.team.id} className="flex-1 border p-4">
                <h3 className="text-lg font-semibold mb-2">{team.team.name}</h3>
              </div>
            ))}
          </div>
        </div>
      ))}
      <button className="bg-green-500 text-white font-bold py-3 px-6 rounded-lg">
        Choose playing 4
      </button>
    </div>
  );
};

export default GroupConfirmation;
