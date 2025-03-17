package org.firstinspires.ftc.team28420.EventSystem;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class EventManager {
    private Map<EventType, List<EventListener>> listeners = new HashMap<>();

    public EventManager() {}

    public void subscribe(EventType ev, EventListener listener) {
        if(ev != null && listener != null)
            listeners.get(ev).add(listener);
    }

    public void unsubscribe(EventType ev, EventListener listener) {
        if(ev != null && listener != null)
            listeners.get(ev).remove(listener);
    }

    public void notifyEvent(EventType ev, EventValue value) {
        for(EventListener listener : listeners.get(ev))
            listener.update(ev, value);
    }
}
