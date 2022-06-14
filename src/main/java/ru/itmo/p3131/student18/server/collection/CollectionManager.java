package ru.itmo.p3131.student18.server.collection;

import ru.itmo.p3131.student18.interim.objectclasses.HumanBeing;
import ru.itmo.p3131.student18.server.Server;
import ru.itmo.p3131.student18.server.exeptions.ObjectFieldsValueException;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.Stack;


public class CollectionManager {
    private final Stack<HumanBeing> stack= new Stack<>();;
    private final LocalDateTime initTime = LocalDateTime.now();

    public CollectionManager()  {
    }

    //Managing and init methods:
    public void init(CollectionLoader collectionLoader) throws ObjectFieldsValueException, SQLException {
        stack.addAll(collectionLoader.execute());
        Collections.sort(stack);
        updateBiggestId();
    }

    public String getType() {
        String[] tokens = stack.getClass().getName().split("\\.");
        return tokens[tokens.length - 1];
    }

    public String getInitTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return initTime.format(formatter);
    }

    public int getSize() {
        return stack.size();
    }

    public Stack<HumanBeing> getStack() {
        return stack;
    }

    //Updating methods:

    /**
     * Method is used only in init() method to set the biggest id value to static int IdCounter.
     */
    public void updateBiggestId() {
        int maxId = HumanBeing.staticId();
        for (HumanBeing human : stack) {
            maxId = Math.max(human.getId(), maxId);
        }
        HumanBeing.idCounterSetter(maxId);
    }

    public int getPositionById(int id) {
        int position = 0;
        for (HumanBeing human : stack) {
            if (id == human.getId()) {
                return position;
            }
            ++position;
        }
        return position;
    }

    public boolean isIdValid(int id) {
        for (HumanBeing human : stack) {
            if (id == human.getId()) {
                return true;
            }
        }
        return false;
    }

    //Methods for execute():
    public void info() {
        Server.printDef("Collection information:" +
                        "\n\tType: " + getType() +
                        "\n\tInitialization time: "+ getInitTime() +
                        "\n\tSize: " + getSize() + "\n");
    }

    /**
     * A method to print each collection element, starting from the heap(if it's stack), to standard output.
     */
    public void show() {
        Server.printDef("\nCollection elements:");
        stack.stream().map(HumanBeing::toString).forEach(Server::printDef);
    }

    public void add(HumanBeing humanBeing) {
        stack.add(humanBeing);
        Collections.sort(stack);
        updateBiggestId();
    }

    public void update(HumanBeing updatedHumanBeing) {
        int position = getPositionById(updatedHumanBeing.getId());
        stack.removeElementAt(position);
        stack.add(position, updatedHumanBeing);
    }

    public void remove_by_id(int id) {
        stack.removeElementAt(getPositionById(id));
        Server.printDef("Element was deleted.");
        updateBiggestId();
    }

    public void remove_first() {
        try {
            stack.pop();
            Server.printDef("First element of collection has been removed.");
            updateBiggestId();
        } catch (EmptyStackException e) {
            Server.printErr("Stack is empty.");
        }
    }

    /**
     * Removes the last element of java.ru.itmo.p3131.student18.collection. This could be performed with "foreach",
     * but it performed the way it meant to be done(by making another stack and dragging
     * elements on it, deleting the last element in real stack and dragging other elements back).
     */
    public void remove_last() {
        Stack<HumanBeing> tmpStack = new Stack<>();
        try {
            while (stack.size() > 1) {
                tmpStack.push(stack.pop());
            }
            stack.pop();
            Server.printDef("Last element of collection has been removed.");
            while (tmpStack.size() > 0) {
                stack.push(tmpStack.pop());
            }
            updateBiggestId();
        } catch (EmptyStackException e) {
            Server.printErr("Stack is empty.");
        }
    }

    public void remove_greater(int id) {
        if (stack.size() > 0) {
            Stack<HumanBeing> tmpStack = new Stack<>();
            while (stack.size() > 0) {
                if (stack.peek().getId() > id) {
                    stack.pop();
                } else {
                    tmpStack.push(stack.pop());
                }
            }
            Server.printDef("All elements greater than " + id + " are deleted.");
            while (tmpStack.size() > 0) {
                stack.push(tmpStack.pop());
            }
            updateBiggestId();
        } else {
            Server.printErr("Stack is empty.");
        }
    }

    public void count_by_impact_speed(float impactSpeed) {
        Server.printDef("Amount of elements with impact speed " + impactSpeed + ": " +
                stack.stream().filter(s -> s.getImpactSpeed() == impactSpeed).count());
    }

    public void filter_starts_with_name(String subName) {
        stack.stream().filter(s -> s.getName().startsWith(subName)).map(HumanBeing::toString).forEach(Server::printDef);
    }

    public void filter_less_than_impact_speed(float impactSpeed) {
        int count = 0;
        for (HumanBeing human : stack) {
            if (human.getImpactSpeed() < impactSpeed) {
                Server.printDef(human.toString());
                count++;
            }
        }
        if (count == 0) {
            Server.printErr("There are no elements with field impact speed less than " + impactSpeed);
        }
    }

    public void clear() {
        stack.clear();
        Server.printDef(   "........／＞　   フ.....................\n" +
                           "　　　　　| 　_　 _|\n" +
                           "　 　　　／`ミ  v 彡\n" +
                           "　　 　 /　　　 　 |\n" +
                           "　　　 /　 ヽ　　 ﾉ\n" +
                           "　／￣|　　 |　|　|\n" +
                           "　| (￣ヽ＿_ヽ_)_)\n" +
                           "　＼二つ       Жалко удалять столько добра...\n");
    }


}
