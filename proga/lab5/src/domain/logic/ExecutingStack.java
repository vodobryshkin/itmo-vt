package domain.logic;

import java.util.ArrayList;

public class ExecutingStack {
    private ArrayList<String> stack;

    public ExecutingStack() {
        stack = new ArrayList<String>();
    }

    public void push(String s) {
        stack.add(s);
    }

    public void pop() {
        if (stack.size() > 0) {
            stack.remove(stack.size() - 1);
        }
    }

    public boolean inStack(String s) {
        for (int i = 0; i < stack.size(); i++) {
            if (stack.get(i).equals(s)) {
                return true;
            }
        }

        return false;
    }

    public boolean isEmpty() {
        return stack.size() == 0;
    }

    @Override
    public String toString() {
        return stack.toString();
    }
}
