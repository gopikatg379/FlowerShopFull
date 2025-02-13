import axios from 'axios'
import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import Navbar from './Navbar'
import './ViewMore.css'
import Footer from './Footer'
import { Link } from 'react-router-dom'
const ViewMore = () => {
    const {id} = useParams()
    const [data,setData] = useState({})
    const fetchData = async()=>{
        try{
            const token = localStorage.getItem("access_token");
            const response = await axios.get(`http://localhost:8080/flowerShop/get/one/${id}`,{
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
   
    <div className="card-container">
      
    <div className="card" key={data.book_id} style={{ height: '500px', width: '350px' }}>
      <img
        className="card-img"
        src={`http://localhost:8080/uploads/${data.image}`}
        alt={data.flower_name}
        
      />
      <div className="card-body">
        <h5 className="card-title">{data.flower_name}</h5>
        <h6 className="card-subtitle">{data.color}</h6>
        <h6 className="card-subtitle">{data.description}</h6>
        <p className="card-text">{data.price}/-</p>
        <Link to={`/cart/${data.flower_id}`}><button className="add-to-cart-btn">Add to Cart</button></Link>
      </div>
    </div>
</div>
<Footer></Footer>
</>
  )
}

export default ViewMore
