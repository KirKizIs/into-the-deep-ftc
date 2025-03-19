package org.firstinspires.ftc.team28420.eventsystem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class EventManager {
    private Map<EventType, List<EventListener>> listeners = new HashMap<>();

    public EventManager() {}

    public void subscribe(EventType ev, EventListener listener) {
        if(ev != null && listener != null) {
            if (!listeners.containsKey(ev))
                listeners.put(ev, new ArrayList<>());
            listeners.get(ev).add(listener);
        }
    }

    public void unsubscribe(EventType ev, EventListener listener) {
        if(ev != null && listener != null) {
            if (!listeners.containsKey(ev))
                return;
            listeners.get(ev).remove(listener);
        }
    }

    public void notifyEvent(EventType ev, EventValue value) {
        if(listeners.get(ev) == null)
            return;
        for(EventListener listener : listeners.get(ev))
            listener.update(ev, value);
    }
}
