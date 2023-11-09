public static Date getbeginingOfDate(Date date) {
  // Create a Calendar object for the given date
  Calendar calendar = Calendar.getInstance();
  calendar.setTime(date);

  // Set the hour, minute, and second to zero
  calendar.set(Calendar.HOUR_OF_DAY, 0);
  calendar.set(Calendar.MINUTE, 0);
  calendar.set(Calendar.SECOND, 0);

  // Add one day to the calendar
  calendar.add(Calendar.DAY_OF_MONTH, 1);

  // Get the end of the date from the calendar
  Date endOfDate = calendar.getTime();

  // Return the end of the date
  return endOfDate;
}



public static Date getEndOfDay(Date date) {
  // Create a Calendar object for the given date
  Calendar calendar = Calendar.getInstance();
  calendar.setTime(date);

  // Set the hour, minute, and second to twenty-three, fifty-nine, and fifty-nine
  calendar.set(Calendar.HOUR_OF_DAY, 23);
  calendar.set(Calendar.MINUTE, 59);
  calendar.set(Calendar.SECOND, 59);

  // Get the end of the day from the calendar
  Date endOfDay = calendar.getTime();

  // Return the end of the day
  return endOfDay;
}
