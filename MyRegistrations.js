import React, {useState, useEffect} from "react";
import { myRegistrations } from "../utils/api";

export default function MyRegistrations({student}){
  const [regs, setRegs] = useState([]);
  useEffect(()=>{ load(); }, []);
  async function load(){
    const data = await myRegistrations(student.studentId);
    setRegs(data || []);
  }
  return (
    <div>
      <h4>My Registrations</h4>
      <ul>
        {regs.map(r => (
          <li key={r.regId}>{r.event.title} — {r.status}</li>
        ))}
      </ul>
    </div>
  );
}
