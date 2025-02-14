import axios from 'axios'
import React, { useEffect, useState } from 'react'
import Navbar from './Navbar'
const MyProfile = () => {
    const [data,setData]=useState({})
    const fetchData = async()=>{
        try{
            const token = localStorage.getItem("access_token");
            const response = await axios.get("http://localhost:8080/user/profile",{
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            })
            console.log(response.data)
            setData(response.data)
            
        }catch(error){
            console.log("There was an error",error)
        }
    }
    useEffect(()=>{
        fetchData()
    },[])
  return (
    <div>
      <Navbar></Navbar>
      <div className="profile-container">
        {/* Sidebar (Vertical Menubar) */}
        <div className="sidebar">
          <img
            className="profile-img"
            src={`http://localhost:8080/uploads/${data.image ? data.image : "default.jpg"}`}
            alt={data.name}
          />
          <span className="profile-name">{data.name ? data.name.toUpperCase() : "Loading..."}</span>
          <ul className="menu">
            <li>Dashboard</li>
            <li>Add</li>
            <li>Wishlist</li>
          </ul>
        </div>

        {/* Profile Content */}
        <div className="profile-content">
          <h2>Welcome, {data.name ? data.name : "User"}!</h2>
        </div>
      </div>
    </div>
  )
}

export default MyProfile
