public class MyCalendar {
    MyTreeMap<Integer, Integer> calendar;
    
    public MyCalendar() {
        calendar = new MyTreeMap<Integer, Integer>();
    }
    
    public boolean book(int start, int end) {
        if(start < 0 || start >= end)
        {
            throw new IllegalArgumentException();
        }
        /**
         * fails if:
         * there's another one starting before it and ending after start
         * starting during
         *
         */
        if(calendar.floorKey(end) != null)
            if(calendar.floorKey(end) < end 
                && calendar.get(calendar.floorKey(end)) > start)
            {
                return false;
            }
        System.out.println("Inserting " + start + " " + end);
        
        calendar.put(start, end);
        return true;
    }

    public MyTreeMap<Integer, Integer> getCalendar(){
        return this.calendar;
    }
}