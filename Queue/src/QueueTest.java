//Im SS 13 in Pr1: Aufgabe 9, in WS 08 in IN3: Aufgabe 6

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/** JUnit4-Testtreiber für Klasse Queue. */
@RunWith(JUnit4.class)
public class QueueTest {

    
    private Queue _createQueue(final int size){
    	return new 
    		//JavaQueue(size)
    		ScalaQueue(size)
    	;
    }
    
    /** Für die Testläufe benutzte Queue-Kapazität */
    private static final int _SIZE = 10;

	private final Queue _queue = _createQueue(_SIZE);

    private static final String[] _alphabet = {"anton","berta","caesar","dora","emil","friedrich","gustav","heinrich","ida","julius"};

    @Test
    public void geradeausBenutzung() throws Queue.Overflow, Queue.Underflow {
        for(final String string: _alphabet){
        	_queue.insert(string);
        }
        try{
        	_queue.insert("kaufmann");
        	fail("Queue.Overflow expected. Queue must have exactly " + _SIZE + " cells.");
        }catch(final Queue.Overflow expected){}
        for(final String expected: _alphabet){
        	final String actual = _queue.read();
        	assertEquals(expected, actual);
        	_queue.delete();
        }
        try{
        	_queue.read();
        	fail("Queue.Underflow expected");
        }catch(final Queue.Underflow expected){}
        try{
        	_queue.delete();
        	fail("Queue.Underflow expected");
        }catch(final Queue.Underflow expected){}
    }

    @Test
    public void readIsInformator() throws Queue.Overflow, Queue.Underflow {
    	for(final String string: _alphabet){
        	_queue.insert(string);
        }
        for(int i=0; i< _alphabet.length; i++){
	    	final String actual = _queue.read();
	    	assertEquals(_alphabet[0], actual);
	    }
    }


    @Test(expected=Queue.Underflow.class)
    public void readUnderflow() throws Queue.Underflow{
        _queue.read();
    }
    @Test(expected=Queue.Underflow.class)
    public void deleteUnderflow() throws Queue.Underflow{
        _queue.delete();
    }

    @Test
    public void gemischtesInsertUndDelete() throws Queue.Overflow, Queue.Underflow {
        for(int i=0; i<_alphabet.length; i+=2){
        	_queue.insert(_alphabet[i]);
        	_queue.insert(_alphabet[i+1]);
        	assertEquals("i="+i, _alphabet[i/2], _queue.read());
        	_queue.delete();
        }
        for(int i=_alphabet.length/2; i<_alphabet.length; i++){
        	assertEquals("i="+i, _alphabet[i], _queue.read());
        	_queue.delete();
        }
    }

    /**Testet das zeitlich verzahnte Benutzen mehrerer Objekte der Klasse Queue.
     * Hierdurch werden insbesondere Fehler aufgedeckt, die durch statische Attribute der Klasse Queue bewirkt werden.*/
    @Test
    public void mehrereQueues() throws Queue.Overflow, Queue.Underflow {
    	final Queue[] queues = {
    			_createQueue(10),
    			_createQueue(10),
    			_createQueue(10),
    			_createQueue(10),
    			_createQueue(10),
    	};
    	//Alle Queues parallel befüllen:
    	for(final String string: _alphabet){
        	for(final Queue queue: queues){
        		queue.insert(string);
        	}
    	}
    	//Jede Queue auf Overflow prüfen:
    	for(final Queue queue: queues){
            try{
            	queue.insert("kaufmann");
            	fail("Queue.Overflow expected");
            }catch(final Queue.Overflow expected){}
    	}//for
    	//Jede Queue einzeln leeren und prüfen:
    	for(final Queue queue: queues){
        	for(final String expected: _alphabet){
            	assertEquals(expected, queue.read());
            	queue.delete();
        	}//for
    	}//for
    	//Jede Queue auf Underflow prüfen:
    	for(final Queue queue: queues){
            try{
            	queue.delete();
            	fail("Queue.Underflow expected");
            }catch(final Queue.Underflow expected){}
    	}//for
    }//mehrereQueues


}
