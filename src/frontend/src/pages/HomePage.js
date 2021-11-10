import { React, useEffect, useState } from 'react';
import { Link } from 'react-router-dom'
import './HomePage.scss';
import { TeamTile } from '../components/TeamTile';

export const HomePage = () => {

  const [teams, setTeams] = useState([]);

  useEffect(
    () => {
      const fetchAllTeams = async () => {
          const response = await fetch(`${process.env.REACT_APP_API_BASE_URL}/teams`);
          const data = await response.json();
          setTeams(data);
          console.log(data);
      };

      fetchAllTeams();
    }, []
  );


  return (
    <div className="HomePage">
      <div className="header-section">
        <h1 className="app-name">IPL Dashboard</h1>
      </div>
      <div className="team-grid">
        {teams.map(team => <Link key={team.id} to={`/teams/${team.teamName}`}><TeamTile key={team.id} teamName={team.teamName}></TeamTile></Link>)}
      </div>


    </div>
  );
}


