const BASE = "http://localhost:8080/api";

export async function studentRegister(payload) {
  const res = await fetch(`${BASE}/student/register`, {
    method: "POST",
    headers: {"Content-Type": "application/json"},
    body: JSON.stringify(payload)
  });
  return res.json();
}

export async function studentLogin(payload) {
  const res = await fetch(`${BASE}/student/login`, {
    method: "POST",
    headers: {"Content-Type": "application/json"},
    body: JSON.stringify(payload)
  });
  return res;
}

export async function fetchEvents() {
  const res = await fetch(`${BASE}/student/events`);
  return res.json();
}

export async function registerEvent(eventId, studentId) {
  const res = await fetch(`${BASE}/student/register-event/${eventId}`, {
    method: "POST",
    headers: {"Content-Type": "application/json"},
    body: JSON.stringify({ studentId: String(studentId) })
  });
  return res;
}

export async function myRegistrations(studentId) {
  const res = await fetch(`${BASE}/student/my-registrations/${studentId}`);
  return res.json();
}

export async function coordinatorLogin(payload) {
  const res = await fetch(`${BASE}/coordinator/login`, {
    method: "POST",
    headers: {"Content-Type": "application/json"},
    body: JSON.stringify(payload)
  });
  return res;
}

export async function createEvent(payload) {
  const res = await fetch(`${BASE}/coordinator/create-event`, {
    method: "POST",
    headers: {"Content-Type": "application/json"},
    body: JSON.stringify(payload)
  });
  return res.json();
}

export async function getEventRegistrations(eventId) {
  const res = await fetch(`${BASE}/coordinator/event-registrations/${eventId}`);
  return res.json();
}

export async function updateRegistration(regId, status) {
  const res = await fetch(`${BASE}/coordinator/update-registration/${regId}`, {
    method: "PUT",
    headers: {"Content-Type": "application/json"},
    body: JSON.stringify({ status })
  });
  return res.json();
}
