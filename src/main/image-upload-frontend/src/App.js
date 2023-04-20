import logo from "./logo.svg";
import "./App.css";
import axios from "axios";
import { useState, useEffect } from "react";
import UserProfiles from "./UserProfiles";

function App() {
   return (
      <div className="App">
         <UserProfiles />
      </div>
   );
}

export default App;
