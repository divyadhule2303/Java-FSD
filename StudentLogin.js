import React, {useState} from "react";
import { studentLogin } from "../utils/api";

export default function StudentLogin({onLogin}){
  const [email,setEmail] = useState('');
  const [password,setPassword] = useState('');
  const [msg,setMsg] = useState('');
  const submit = async (e) => {
    e.preventDefault();
    const res = await studentLogin({email,password});
    if(res.status === 200){
      const data = await res.json();
      onLogin(data);
    } else {
      setMsg("Login failed");
    }
  };
  return (
    <div>
      <h4>Login</h4>
      <form onSubmit={submit}>
        <input placeholder="Email" value={email} onChange={e=>setEmail(e.target.value)} /><br/>
        <input placeholder="Password" type="password" value={password} onChange={e=>setPassword(e.target.value)} /><br/>
        <button type="submit">Login</button>
      </form>
      <p>{msg}</p>
    </div>
  );
    }
