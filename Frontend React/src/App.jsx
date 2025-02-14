import { Route,Routes} from 'react-router-dom';
import Home from './components/Home';
import Add from './components/Add';
import ViewMore from './components/ViewMore';
import Register from './components/Register';
import Login from './components/Login';
import Logout from './components/Logout';
import MyProfile from './components/MyProfile';
import SearchResults from './components/SearchResults';
import AddCart from './components/AddCart';
import ViewCart from './components/ViewCart';
import DeleteCart from './components/DeleteCart';
function App() {
  

  return (
    <>
      <Routes>
        <Route path='/' element={<Login></Login>}></Route>
        <Route path='/home' element={<Home></Home>}></Route>
        <Route path='/add' element={<Add></Add>}></Route>
        <Route path='/register' element={<Register></Register>}></Route>
        <Route path='/view/:id' element={<ViewMore></ViewMore>}></Route>
        <Route path='/logout' element={<Logout></Logout>}></Route>
        <Route path='/profile' element={<MyProfile></MyProfile>}></Route>
        <Route path="/search" element={<SearchResults />} />
        <Route path="/cart/:id" element={<AddCart />} />
        <Route path='/view/cart' element={<ViewCart/>}></Route>
        <Route path='delete/cart/:id' element={<DeleteCart/>}></Route>
      </Routes>
    </>
  )
}

export default App
