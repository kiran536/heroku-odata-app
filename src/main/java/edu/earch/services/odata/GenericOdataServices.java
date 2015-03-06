package main.java.edu.earch.services.odata;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import main.java.edu.earch.models.SimpleElement;
import main.java.edu.earch.models.SimpleObject;
import main.java.edu.earch.models.SimpleRequest;

import org.core4j.Enumerable;
import org.odata4j.consumer.ODataConsumer;
import org.odata4j.core.EntitySetInfo;
import org.odata4j.core.OEntity;
import org.odata4j.core.OProperty;
import org.odata4j.edm.EdmSimpleType;
import org.odata4j.jersey.consumer.ODataJerseyConsumer;

import com.google.gson.Gson;

@Path("/odata")
public class GenericOdataServices {

	@POST
	@Path("/getFirstLevelEntities")
	@Produces(MediaType.APPLICATION_JSON)
	public Response printOutFirstEntities(String sEndPoint) {
		// String endpoint =
		// "http://services.odata.org/Northwind/Northwind.svc/";
		System.out.println("The endpoint passed is-->" + sEndPoint);
		String endpoint = sEndPoint;
		ODataConsumer c = ODataJerseyConsumer.newBuilder(endpoint).build()
				.create(endpoint);
		List<SimpleElement> lstToReturn = new ArrayList<SimpleElement>();
		for (EntitySetInfo entitySet : c.getEntitySets()) {
			SimpleElement elem = new SimpleElement(entitySet.getTitle(),
					entitySet.getHref());
			lstToReturn.add(elem);
		}
		System.out.println("The returned list size:"+lstToReturn.size());
		return Response.ok(new Gson().toJson(lstToReturn)).
				header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS").header("Allow","OPTIONS").build();
	}

	@POST
	@Path("/getSecondLevelEntities")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getNextSetOfEntities(String sRequest) {
		SimpleRequest request = new Gson().fromJson(sRequest,
				SimpleRequest.class);
		String endpoint = request.getEndPoint();
		System.out.println(request.getEndPoint() + "---->"
				+ request.getCategory());
		ODataConsumer c = ODataJerseyConsumer.newBuilder(endpoint).build()
				.create(endpoint);
		Enumerable<OEntity> entities = c.getEntities(request.getCategory())
				.execute();
		List<List<SimpleObject>> lstToReturn = new ArrayList<List<SimpleObject>>();
		for (OEntity entity : entities) {
			List<OProperty<?>> set = entity.getProperties();
			List<SimpleObject> lstObject = new ArrayList<SimpleObject>();
			for (OProperty<?> p : entity.getProperties()) {
				Object v = p.getValue();
				if (p.getType().equals(EdmSimpleType.BINARY) && v != null) {
					v = org.odata4j.repack.org.apache.commons.codec.binary.Base64
							.encodeBase64String((byte[]) v).trim();
				}
				SimpleObject objAdd = null;
				if(v!=null){
					 objAdd = new SimpleObject(p.getName(),
						v.toString(),p.getType().getFullyQualifiedTypeName());
					 //System.out.println("Adding:" + p.getName() +" Value:" +v.toString());
				}else{
					objAdd = new SimpleObject(p.getName(),
							"",p.getType().getFullyQualifiedTypeName());
					//System.out.println("Adding:" + p.getName());
				}
				lstObject.add(objAdd);
			}
			lstToReturn.add(lstObject);
		}
		return Response.ok(new Gson().toJson(lstToReturn)).
				header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS").header("Allow","OPTIONS").build();
	}
}
