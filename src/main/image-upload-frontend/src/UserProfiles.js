import logo from "./logo.svg";
import "./App.css";
import axios from "axios";
import { useState, useEffect } from "react";
import React, { useCallback } from "react";
import { MyDropzone } from "./MyDropzone";

const UserProfiles = () => {
   const [userProfile, setUserProfile] = useState([]);
   const fetchUserProfiles = () => {
      axios.get("http://localhost:8080/api/v1/user-profile").then((res) => {
         const data = res.data;
         setUserProfile(data);
      });
   };

   useEffect(() => {
      fetchUserProfiles();
   }, []);

   return userProfile.map((userProfile, index) => {
      return (
         <div key={index}>
            {userProfile.userProfileId ? (
               <img
                  src={`http://localhost:8080/api/v1/${userProfile.userProfileId}/image/download`}
               />
            ) : null}
            <br />
            <br />
            <h1>{userProfile.userName}</h1>
            <p>{userProfile.userProfileId}</p>
            <MyDropzone userProfileId={userProfile.userProfileId} />
            <br />
         </div>
      );
   });
};

export default UserProfiles;
