import React, { useState } from "react";
import StudentRegister from "./components/StudentRegister";
import StudentLogin from "./components/StudentLogin";
import EventList from "./components/EventList";
import MyRegistrations from "./components/MyRegistrations";
import CreateEvent from "./components/CreateEvent";

function App() {
  const [student, setStudent] = useState(null);
  const [coord, setCoord] = useState(null);

  return (
    <div style={{padding:20}}>
      <h2>College Event Management</h2>

      <div style={{display:'flex', gap:20}}>
        <div style={{flex:1}}>
          <h3>Student</h3>
          {!student && <StudentRegister />}
          {!student && <StudentLogin onLogin={(s)=> setStudent(s)} />}
          {student && <div>
            <p>Logged in as: {student.name} ({student.email})</p>
            <button onClick={()=> setStudent(null)}>Logout</button>
            <EventList student={student} />
            <MyRegistrations student={student} />
          </div>}
        </div>

        <div style={{flex:1}}>
          <h3>Coordinator</h3>
          {!coord && <CreateEvent onLogin={(c)=> setCoord(c)} showLoginOnly={true} />}
          {coord && <div>
            <p>Coordinator: {coord.name}</p>
            <CreateEvent coord={coord} />
          </div>}
        </div>
      </div>
    </div>
  );
}

export default App;
