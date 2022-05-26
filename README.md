# Pharmacy_Project_Front
The front end part of Pharmacy project.

The front end part of the project it is implemented in Android Studio, using Kotlin, Java classes. The application is tested on an Emulator but also on my personal phone.

The current application consists in a login activity, which redirect by the role of the introduced account. A new activity is open for an employee account,
and another activity is open for the client.
To begin with Employee activity. It has 4 buttons: ADD DELETE UPDATE and VIEW product. Pressing one of those, will create a new activity. For Add and Update activity, 
there are fields to write the characteristics of a new product, or to update a current product. As for the Delete, View and Update, there is a list which contains the 
products in database. Click on one product and it will updated/deleted in the moment of pressing the action button.

How are the requests formed? The link between frontend and backend

In each activity the user introduce what he wants to do, so we have the input. Now to pass the input to the backend part, and to form the request, 
and to receive the response. For this I use Retrofit and Gson. 
I created the same classes as my model form back end part. Why because Retrofit automatically serialises the JSON response using a POJO(Plain Old Java Object) 
which must be defined in advanced for the JSON Structure. To serialise JSON we need a converter to convert it into Gson first.

This part will create a retrofit, we pass the base url, which is localhost, but in order to work properly we have to put it on 10.0.2.2, specify the converter 
and build it.
And after to create the retrofit interface. In this interface Retrofit provides with a list 
of annotations for each of the HTTP methods: @GET, @POST, @PUT, @DELETE, @PATCH or @HEAD. In the those interfaces (JsonPlaceHolderApi for instance), we’ve defined some methods that perform HTTP requests with annotation. 

@Field – send data as form-urlencoded. This requires a @FormUrlEncoded annotation attached with the method.
@Body – Sends Java objects as request body.

Running the application would call each of the endpoints and display a Toast message for them accordingly.





