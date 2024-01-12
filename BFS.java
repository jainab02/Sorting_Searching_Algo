import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class BFS {
    Queue<String> agenda = new LinkedList<String>(); // Use a Queue Implemented using LinkedList for storing all the nodes in BFS.
    Map<String, Integer> stateDepth = new HashMap<String, Integer>(); // HashMap is used to ignore repeated nodes
    Map<String, String> stateHistory = new HashMap<String, String>(); // Relates each position to its predecessor

    public static void main(String args[]) {
        String initialState = "876543210";
        BFS bfs = new BFS(); // Create a new instance of the EightPuzzle
        bfs.add(initialState, null); // Add the initial state to the queue
        while (!bfs.agenda.isEmpty()) {
            String currentState = bfs.agenda.remove();
            bfs.up(currentState); // Move the blank space up and add the new state to the queue
            bfs.down(currentState); // Move the blank space down
            bfs.left(currentState); // Move left
            bfs.right(currentState); // Move right and remove the current node from the queue
        }
        System.out.println("Solution doesn't exist");
    }

    // Add method to add the new string to the Map and Queue
    void add(String newState, String oldState) {
        if (!stateDepth.containsKey(newState)) {
            int newValue = (oldState == null) ? 0 : stateDepth.get(oldState) + 1;
            stateDepth.put(newState, newValue);
            agenda.add(newState);
            stateHistory.put(newState, oldState);
        }
    }

    void up(String currentState) {
        int blankIndex = currentState.indexOf("0");
        if (blankIndex > 2) {
            String nextState = currentState.substring(0, blankIndex - 3) +
                "0" +
                currentState.substring(blankIndex - 2, blankIndex) +
                currentState.charAt(blankIndex - 3) +
                currentState.substring(blankIndex + 1);
            checkCompletion(currentState, nextState);
        }
    }

    void down(String currentState) {
        int blankIndex = currentState.indexOf("0");
        if (blankIndex < 6) {
            String nextState = currentState.substring(0, blankIndex) +
                currentState.substring(blankIndex + 3, blankIndex + 4) +
                currentState.substring(blankIndex + 1, blankIndex + 3) +
                "0" +
                currentState.substring(blankIndex + 4);
            checkCompletion(currentState, nextState);
        }
    }

    void left(String currentState) {
        int blankIndex = currentState.indexOf("0");
        if (blankIndex != 0 && blankIndex != 3 && blankIndex != 6) {
            String nextState = currentState.substring(0, blankIndex - 1) +
                "0" +
                currentState.charAt(blankIndex - 1) +
                currentState.substring(blankIndex + 1);
            checkCompletion(currentState, nextState);
        }
    }

    void right(String currentState) {
        int blankIndex = currentState.indexOf("0");
        if (blankIndex != 2 && blankIndex != 5 && blankIndex != 8) {
            String nextState = currentState.substring(0, blankIndex) +
                currentState.charAt(blankIndex + 1) +
                "0" +
                currentState.substring(blankIndex + 2);
            checkCompletion(currentState, nextState);
        }
    }

    private void checkCompletion(String oldState, String newState) {
        add(newState, oldState);
        if (newState.equals("123456780")) {
            System.out.println("Solution Exists at Level " + stateDepth.get(newState) + " of the tree");
            String traceState = newState;
            while (traceState != null) {
                System.out.println(traceState + " at " + stateDepth.get(traceState));
                traceState = stateHistory.get(traceState);
            }
            System.exit(0);
        }
    }
}
