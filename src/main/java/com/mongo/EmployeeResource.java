package com.mongo;


import com.yammer.metrics.annotation.Timed;
import net.vz.mongodb.jackson.DBCursor;
import net.vz.mongodb.jackson.JacksonDBCollection;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/employee")
public class EmployeeResource {

    private JacksonDBCollection<EmployeeData, String> collection;

    public EmployeeResource(JacksonDBCollection<EmployeeData, String> collection) {
        this.collection = collection;
    }

    @Path("/publish")
    @POST
    @Produces(value = MediaType.APPLICATION_JSON)
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Timed
    public Response publishEmployeeData(@Valid EmployeeData data) {
        collection.insert(data);
        return Response.ok().entity("\"Success\"").type(MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/fetch")
    @Produces(value = MediaType.APPLICATION_JSON)
    @Timed
    public List<EmployeeData> fetchEmployeeData() {
        DBCursor<EmployeeData> dbCursor = collection.find();
        List<EmployeeData> datas = new ArrayList<>();
        while (dbCursor.hasNext()) {
            EmployeeData data = dbCursor.next();
            datas.add(data);
        }
        return datas;
    }

}