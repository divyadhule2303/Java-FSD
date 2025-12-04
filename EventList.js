import React, {useState, useEffect} from "react";
import { fetchEvents, registerEvent } from "../utils/api";

export default function EventList({student}){
  const [events, setEvents] = useState([]);
  useEffect(()=>{ load(); }, []);
  async function load(){
    const data = await fetchEvents();
    setEvents(data || []);
  }
  async function handleRegister(eventId){
    const res = await registerEvent(eventId, student.studentId);
    if(res.status === 200) {
      alert("Registered successfully");
    } else {
      const txt = await res.text();
      alert("Error: " + txt);
    }
  }
  return (
    <div>
      <h4>Events</h4>
      <ul>
        {events.map(ev => (
          <li key={ev.eventId}>
            <b>{ev.title}</b> - {ev.date} at {ev.venue} <br/>
            <button onClick={()=> handleRegister(ev.eventId)}>Register</button>
          </li>
        ))}
      </ul>
    </div>
  );
}
