import React, {useState} from "react";
import { studentRegister } from "../utils/api";

export default function StudentRegister(){
  const [form, setForm] = useState({name:'', email:'', password:'', branch:'', year:2});
  const [msg, setMsg] = useState('');
  const submit = async (e) => {
    e.preventDefault();
    const res = await studentRegister(form);
    setMsg("Registered! ID: " + (res.studentId || ""));
  };
  return (
    <div>
      <h4>Register</h4>
      <form onSubmit={submit}>
        <input placeholder="Name" value={form.name} onChange={e=>setForm({...form, name:e.target.value})} required/><br/>
        <input placeholder="Email" value={form.email} onChange={e=>setForm({...form, email:e.target.value})} required/><br/>
        <input placeholder="Password" type="password" value={form.password} onChange={e=>setForm({...form, password:e.target.value})} required/><br/>
        <input placeholder="Branch" value={form.branch} onChange={e=>setForm({...form, branch:e.target.value})}/><br/>
        <input placeholder="Year" type="number" value={form.year} onChange={e=>setForm({...form, year:parseInt(e.target.value)})}/><br/>
        <button type="submit">Register</button>
      </form>
      <p>{msg}</p>
    </div>
  );
    }
