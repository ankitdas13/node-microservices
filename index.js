const express = require("express")
const cors = require("cors")
const app = express();


app.use(cors())

app.get("/",(request, response)=>{
    response.send('api test v2...')
 })

app.get("/posts", async (request, response)=>{
   const data = await fetch("https://jsonplaceholder.typicode.com/posts")
   const resp = await data.json();
   response.json(resp)
})

app.listen(3000,()=>{
    console.log(`server is running on port ${3000}`)
})
