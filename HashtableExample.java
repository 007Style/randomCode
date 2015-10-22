

/*

Java Hashtable example.

This Java Hashtable example describes the basic operation performed on the hashtable.

*/

import java.util.Hashtable;

import java.util.Enumeration;

public class HashtableExample{

public static void main(String args[]){

// constructs a new empty hashtable with default initial capacity

Hashtable hashtable = new Hashtable();

/*

To specify initial capacity, use following constructor.

 Hashtable hashtable = new Hashtable(100);

 To create hashtable from map use following constructor

 Hashtable hashtable = new Hashtable(Map myMap);

 */

hashtable.put( "One", new Integer(1) ); // adding value into hashtable

hashtable.put( "Two", new Integer(2) );

hashtable.put( "Three", new Integer(3) );

hashtable.put( "endo", new Integer(22) );

hashtable.put( "endo", new Integer(99) );

hashtable.put( "endo", new Integer(923413459) );
/*

IMPORTANT : We CAN NOT add primitives to the hashtable. We have to wrap it into one of the wrapper before adding.

*/

/*

To copy all key - value pairs from Map to Hashtable use putAll method.

Signature of putAll method is,

void putAll(Map m)

*/

//get number of keys present in the hashtable

System.out.println("Hashtable contains " + hashtable.size() + " key value pair.");

/*

To check whether Hashtable is empty or not, use isEmpty() method.

isEmpty() returns true is Hashtable is empty, otherwise false.

*/

/*

Finding particular value from the Hashtable :

Hashtable's contains method returns boolean depending upon the presense of the value in given hashtable

Signature of the contains method is,

boolean contains(Object value)

*/

if( hashtable.contains( new Integer(1) ) ){

System.out.println("Hashtable contains 1 as value");

}else{

System.out.println("Hashtable does not contain 1 as value");

}

/*

Finding particular Key from the Hashtable :

Hashtable's containsKey method returns boolean depending upon the presense of the key in given hashtable

Signature of the method is,

boolean containsKey(Object key)

*/

if( hashtable.containsKey("One") ){

System.out.println("Hashtable contains One as key");

}else{

System.out.println("Hashtable does not contain One as value");

}

/*

Use get method of Hashtable to get value mapped to particular key.

Signature of the get method is,

Object get(Object key)

*/

Integer one = (Integer) hashtable.get("One");

System.out.println("Value mapped with key \"One\" is " + one);

/*

IMPORTANT:  get method returns Object, so we need to downcast it.

*/

/*

To get all keys stored in Hashtable use keys method.

Signature of the keys method is,

Enumeration keys()

To get Set of the keys use keySet() method instead.

Set keySet()

*/

System.out.println("Retriving all keys from the Hashtable");

Enumeration e = hashtable.keys();

while( e. hasMoreElements() ){

System.out.println( e.nextElement() );

}

/*

To get all values stored in Hashtable     use elements method.

Signature of the elements method is,

Enumeration elements()

To get Set of the values use entrySet() method instead.

Set entrySet()

*/

System.out.println("Retriving all values from the Hashtable");

e = hashtable.elements();

while( e. hasMoreElements() ){

System.out.println( e.nextElement() );

}

/*

To remove particular key - value pair from the Hashtable use remove method.

Signature of remove methid is,

Object remove(Object key)

This method returns value that had mapped to the given key, otherwise null if mapping not found.

*/

System.out.println( hashtable.remove("One") + " is removed from the Hashtable.");

}

}

/*

OUTPUT of the above given Java Hashtable Example would be :

Hashtable contains 3 key value pair.

Hashtable contains 1 as value

Hashtable contains One as key

Value mapped with key "One" is 1

Retriving all keys from the Hashtable

One

Three

Two

Retriving all values from the Hashtable

1

3

2

1 is removed from the Hashtable.

*/
