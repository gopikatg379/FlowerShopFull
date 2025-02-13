import React, { useState } from "react";
import "./Navbar.css"; // Import the CSS file
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";

const Navbar = () => {
  const [dropdownOpen, setDropdownOpen] = useState(false);
  const[searchQuery,setSearchQuery] = useState("");
  const navigate = useNavigate();
  const handleSearch = async(e)=>{
    e.preventDefault(); // Prevent form submission reloading the page
    if (!searchQuery.trim()) return; // Prevent empty search
    try{
      const token = localStorage.getItem("access_token");
      const response = await axios.get(`http://localhost:8080/flowerShop/search?flower=${searchQuery}`,{
        headers: {
          Authorization: `Bearer ${token}`
      }
      })
      navigate(`/search?query=${searchQuery}`, { state: { results: response.data } });
    }catch(error){
      console.log("There was an error",error)
    }
  }
  const toggleDropdown = () => {
    setDropdownOpen(!dropdownOpen);
  };

  return (
    <nav className="navbar">
      <div className="logo">
        <span style={{ color: "red" }}>Flower</span>
        <span style={{ color: "green" }}>Mart</span>
      </div>

      <div className="nav-links">
        <Link to="/home">Home</Link>
        <a href="#">Contact</a>
      </div>

      <form className="search-container" onSubmit={handleSearch}>
        <input
          type="text"
          placeholder="Search flowers..."
          value={searchQuery}
          onChange={(e) => setSearchQuery(e.target.value)}
        />
        <button type="submit">
          <i className="fas fa-search"></i>
        </button>
      </form>

      <div className="icons">
        <Link to='/view/cart' style={{color:'black'}}><i className="fas fa-shopping-cart"></i></Link>

        {/* User Icon with Dropdown */}
        <div className="user-dropdown">
          <i className="fas fa-user" onClick={toggleDropdown}></i>
          {dropdownOpen && (
            <div className="dropdown-menu">
              <Link to="/profile">My Profile</Link>
              <Link to="/logout">Logout</Link>
            </div>
          )}
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
