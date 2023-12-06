import React, { useState } from "react";
import "tailwindcss/tailwind.css";
import { Link } from "react-router-dom";

const Tournament = () => {
  const [tournamentName, setTournamentName] = useState("");
  const [maxGroups, setMaxGroups] = useState("");
  const [maxTeams, setMaxTeams] = useState("");
  const [popupMessage, setPopupMessage] = useState("");

  const handleInputChange = (e, setter) => {
    setter(e.target.value);
  };

  const closePopup = () => {
    setPopupMessage("");
    console.log(popupMessage);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const data = {
      name: tournamentName,
      maxTeams: maxTeams,
      maxGroups: maxGroups,
    };

    try {
      const response = await fetch("http://localhost:8181/tournament", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
      });

      if (response.ok) {
        setPopupMessage("Tournament created successfully!");
        setMaxGroups("");
        setMaxTeams("");
        setTournamentName("");
      } else {
        setPopupMessage("Error while creating the tournament");
      }
    } catch (error) {
      console.error("Error:", error);
      setPopupMessage("Error while creating the tournament");
    }
  };

  return (
    <div className="flex items-center justify-center h-screen bg-purple-500 text-3xl">
      <form
        className="bg-orange-300 p-8 rounded shadow-2xl w-4/6"
        onSubmit={handleSubmit}
      >
        <div className="mb-4 flex items-center">
          <label
            htmlFor="tournamentName"
            className="w-1/4 text-gray-700 font-bold mr-2 text-3xl"
          >
            Tournament Name
          </label>
          <input
            type="text"
            id="tournamentName"
            value={tournamentName}
            onChange={(e) => handleInputChange(e, setTournamentName)}
            className="w-3/4 px-3 py-2 border rounded-md focus:outline-none focus:shadow-outline-blue focus:border-blue-300 transition-all duration-300"
          />
        </div>

        <div className="mb-4 flex items-center">
          <label
            htmlFor="maxGroups"
            className="w-1/4 text-gray-700 text-3xl font-bold mr-2"
          >
            Maximum Groups
          </label>
          <input
            type="number"
            id="maxGroups"
            value={maxGroups}
            onChange={(e) => handleInputChange(e, setMaxGroups)}
            className="w-3/4 px-3 py-2 border rounded-md focus:outline-none focus:shadow-outline-blue focus:border-blue-300 transition-all duration-300"
          />
        </div>

        <div className="mb-4 flex items-center">
          <label
            htmlFor="maxTeams"
            className="w-1/4 text-gray-700 text-3xl font-bold mr-2"
          >
            Maximum Teams
          </label>
          <input
            type="number"
            id="maxTeams"
            value={maxTeams}
            onChange={(e) => handleInputChange(e, setMaxTeams)}
            className="w-3/4 px-3 py-2 border rounded-md focus:outline-none focus:shadow-outline-blue focus:border-blue-300 transition-all duration-300"
          />
        </div>

        <div className="flex items-center justify-center">
          <button
            type="submit"
            className="bg-green-500 text-white px-6 py-3 rounded hover:bg-green-700 focus:outline-none focus:shadow-outline-blue active:bg-green-800 transform transition-all duration-300"
          >
            Submit
          </button>
        </div>
      </form>
      {popupMessage && (
        <div className="fixed top-0 left-0 w-full h-full flex items-center justify-center">
          <div className="bg-gray-900 bg-opacity-50 absolute w-full h-full"></div>
          <div className="bg-white p-8 rounded shadow-md text-xl text-center relative z-10">
            <p>{popupMessage}</p>
            <Link to="/">
              <button
                className="mt-4 bg-blue-500 text-white px-4 py-2 rounded-full hover:bg-blue-700 focus:outline-none focus:shadow-outline-blue active:bg-blue-800"
                onClick={closePopup}
              >
                Close
              </button>
            </Link>
          </div>
        </div>
      )}
    </div>
  );
};

export default Tournament;
