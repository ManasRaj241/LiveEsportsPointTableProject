import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";

const Home = () => {
  const [tournaments, setTournaments] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8181/tournament")
      .then((response) => response.json())
      .then((data) => setTournaments(data))
      .catch((error) => {
        console.error("Error fetching tournament data:", error);
      });
  }, []);

  const handleDelete = (tournamentId) => {
    fetch(`http://localhost:8181/tournament/${tournamentId}`, {
      method: "DELETE",
    })
      .then((response) => {
        if (response.status === 204) {
          setTournaments((prevTournaments) =>
            prevTournaments.filter(
              (tournament) => tournament.id !== tournamentId
            )
          );
        } else {
          return response.json();
        }
      })
      .then((data) => {
        if (data) {
          console.error("Error deleting tournament:", data);
        }
      })
      .catch((error) => {
        console.error("Error deleting tournament:", error);
      });
  };

  return (
    <div className=" bg-purple-100 flex h-screen items-center justify-center">
      <div className="flex justify-center w-1/3 p-8">
        <Link to="/tournament">
          <button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
            Add New Tournament Name
          </button>
        </Link>
      </div>

      <div className="w-2/3 p-8">
        <h2 className="text-2xl font-bold mb-4">Recent Tournaments</h2>

        <table className="min-w-full divide-y divide-gray-200">
          <thead className="bg-gray-50">
            <tr>
              <th
                scope="col"
                className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
              >
                Sl number
              </th>
              <th
                scope="col"
                className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
              >
                Name
              </th>
              <th
                scope="col"
                className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
              >
                Delete
              </th>
              <th
                scope="col"
                className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
              >
                View
              </th>
            </tr>
          </thead>
          <tbody className="bg-white divide-y divide-gray-200">
            {tournaments.map((tournament, index) => (
              <tr key={index}>
                <td className="px-6 py-4 whitespace-nowrap">{index + 1}</td>
                <td className="px-6 py-4 whitespace-nowrap">
                  {tournament.name}
                </td>
                <td className="px-6 py-4 whitespace-nowrap">
                  <button
                    className="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded"
                    onClick={() => handleDelete(tournament.id)}
                  >
                    Delete
                  </button>
                </td>
                <td className="px-6 py-4 whitespace-nowrap">
                  <Link to={`/SelectTeams/${tournament.id}`}>
                    <button className="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded">
                      View
                    </button>
                  </Link>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default Home;
