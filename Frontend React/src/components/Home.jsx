import React, { useEffect, useState } from 'react'
import 'bootstrap/dist/css/bootstrap.min.css';
import axios from 'axios';
import { Card,Button } from 'react-bootstrap';
import './Home.css'
import Navbar from './Navbar';
import { Link } from 'react-router-dom';
import Footer from './Footer';
const Home = () => {
    const [data,setData] = useState([])
    const[error,setError]=useState(null)
    const fetchData = async()=>{
        try{
            const token = localStorage.getItem("access_token");
            if(!token){
              setError('no access found')
              return;
            }
            const response = await axios.get("http://localhost:8080/flowerShop/get/flowers",{
              headers: {
                Authorization: `Bearer ${token}`
            }
            })
            setData(response.data)
            
        }catch(error){
            console.log("There was an error",error)
        }
    }
    useEffect(()=>{
        fetchData()
    },[])
  return (
    <>
    <Navbar></Navbar>
    <div className="card1-container">
      
      {error && <Alert variant="danger">{error}</Alert>}
  {data.map((x) => (
    <div className="card1" key={x.book_id}>
      <img
        className="card-img"
        src={`http://localhost:8080/uploads/${x.image}`}
        alt={x.flower_name}
      />
      <div className="card1-body">
        <h5 className="card1-title">{x.flower_name}</h5>
        <h6 className="card1-subtitle">{x.color}</h6>
        <p className="card1-text">{x.price}/-</p>
        <Link to={`/view/${x.flower_id}`}><button className="btn1-login">View More</button></Link>
      </div>
    </div>
  ))}  
</div>
<Footer></Footer>
</>
  )
}

export default Home
