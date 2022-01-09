## Collision World

> ### Info

Name: Liangying Huang <br>
Heroku app url: https://lh49-hw4-collision.herokuapp.com/ <br>


> ### Design Decision Report
#### Model
* Utilized union design pattern to define a paint object class and its subclasses.
* Designed two objects moving strategies with composite design patterns.
* Leveraged factory design pattern to create objects' moving and interaction behaviors and commands that objects received.
* Defined the null strategy and null object to deal with unknown conditions in object or strategy making.
* Implemented command design pattern to make the implementation of property changes on objects more extensible and robust.

---

#### Controller
* It redirects unknown urls to root url to avoid 404 Page Not Found Error.
* To separate API designs with actual interaction to the model, the controller owns a dispatch adapter as a proxy to communicate with ball world.
* Following the previous point, the dispatch adapter takes charge of a ball world store, processing requests made by the controller and operating them on its store.

---

#### View

* In HTML files, I use selection elements and let users choose object behaviors and strategies by it to avoid making too many buttons. The JQuery selector identity the selected attribute value and the "Add" button pass the value to the backend to generate correct object.
* To draw concentric shape on a ball with the concentric interaction strategy. I utilized draw polygon function to draw polygon with different sides (basically reuse hw2's code to save development time).
