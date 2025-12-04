import React, {useState} from "react";
import { coordinatorLogin, createEvent, getEventRegistrations, updateRegistration } from "../utils/api";

export default function CreateEvent({coord, onLogin, showLoginOnly}){
  const [form,setForm] = useState({title:'', description:'', date:'', venue:'', capacity:100, coordId: coord ? coord.coordId : ''});
  const [login, setLogin] = useState({email:'', password:''});
  const [msg, setMsg] = useState('');
  const [regs, setRegs] = useState([]);
  const [eventIdToView, setEventIdToView] = useState('');

  const doLogin = async (e) => {
    e.preventDefault();
    const res = await coordinatorLogin(login);
    if(res.status === 200) {
      const data = await res.json();
      if(onLogin) onLogin(data);
      setMsg("Coordinator logged in");
      setForm({...form, coordId: data.coordId});
    } else {
      setMsg("Invalid coordinator login");
    }
  };

  const doCreate = async (e) => {
    e.preventDefault();
    if(!form.coordId){ setMsg("Provide coordinator ID (login first)"); return; }
    const payload = {...form, coordId: String(form.coordId)};
    const res = await createEvent(payload);
    setMsg("Event created: " + res.title);
  };

  const viewRegs = async () => {
    if(!eventIdToView) return;
    const data = await getEventRegistrations(eventIdToView);
    setRegs(data || []);
  };

  const update = async (regId, status) => {
    await updateRegistration(regId, status);
    viewRegs();
  };

  if(showLoginOnly){
    return (
      <div>
        <h4>Coordinator Login</h4>
        <form onSubmit={doLogin}>
          <input placeholder="email" value={login.email} onChange={e=>setLogin({...login,email:e.target.value})} /><br/>
          <input placeholder="password" type="password" value={login.password} onChange={e=>setLogin({...login,password:e.target.value})} /><br/>
          <button type="submit">Login</button>
        </form>
        <p>{msg}</p>
      </div>
    );
  }

  return (
    <div>
      <h4>Create Event</h4>
      <form onSubmit={doCreate}>
        <input placeholder="Title" value={form.title} onChange={e=>setForm({...form, title:e.target.value})} /><br/>
        <textarea placeholder="Description" value={form.description} onChange={e=>setForm({...form, description:e.target.value})} /><br/>
        <input type="date" value={form.date} onChange={e=>setForm({...form, date:e.target.value})} /><br/>
        <input placeholder="Venue" value={form.venue} onChange={e=>setForm({...form, venue:e.target.value})} /><br/>
        <input placeholder="Capacity" type="number" value={form.capacity} onChange={e=>setForm({...form, capacity:parseInt(e.target.value)})} /><br/>
        <button type="submit">Create</button>
      </form>
      <p>{msg}</p>

      <hr/>
      <h4>View Registrations</h4>
      <input placeholder="Event ID" value={eventIdToView} onChange={e=>setEventIdToView(e.target.value)} />
      <button onClick={viewRegs}>View</button>
      <ul>
        {regs.map(r => (
          <li key={r.regId}>
            {r.student.name} — {r.status}
            <button onClick={()=> update(r.regId, "APPROVED")}>Approve</button>
            <button onClick={()=> update(r.regId, "REJECTED")}>Reject</button>
          </li>
        ))}
      </ul>
    </div>
  );
    }
