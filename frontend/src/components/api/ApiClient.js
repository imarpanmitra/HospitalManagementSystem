import axios from "axios";

export const clientApi = axios.create({ baseURL: "http://localhost:8083" });

// export const clientApiSecond = axios.create({ baseURL: "http://localhost:8081" });