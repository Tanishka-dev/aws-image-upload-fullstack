import logo from "./logo.svg";
import "./App.css";
import axios from "axios";
import { useState, useEffect } from "react";
import React, { useCallback } from "react";
import { useDropzone } from "react-dropzone";
import UserProfiles from "./UserProfiles";

export function MyDropzone(props) {
   const onDrop = useCallback((acceptedFiles) => {
      const file = acceptedFiles[0];
      console.log(file);
      const formData = new FormData();
      formData.append("file", file); //equal to requestParam

      axios
         .post(
            `http://localhost:8080/api/v1/${props.userProfileId}/image/upload`,
            formData,
            {
               headers: { "Content-Type": "multipart/form-data" },
               mode: "no-cors",
            }
         )
         .then(() => {
            console.log("Success");
         })
         .catch((err) => {
            console.log(err);
         });
   }, []);
   const { getRootProps, getInputProps, isDragActive } = useDropzone({
      onDrop,
   });

   return (
      <div {...getRootProps()}>
         <input {...getInputProps()} />
         {isDragActive ? (
            <p>Drop the image here ...</p>
         ) : (
            <p>Drag 'n' drop profile image or click to select</p>
         )}
      </div>
   );
}
