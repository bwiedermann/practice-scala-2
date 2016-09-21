package exercises

import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL.Parse._
import net.ruippeixotog.scalascraper.model.Element

object WebScraper extends App {
  
  /** Website for the Hive events */
  val HIVE_URL = "http://creativity.claremont.edu/?post_type=tribe_events"

  /**
   * CSS selectors for events from the Hive website. I extracted this 
   * information from the Hive's webpage on Sept. 20, 2016. Here's an example
   * event:
   * <div class='av-tribe-events-content-wrap'>    
        <h2 class="tribe-events-list-event-title">
            <a class="tribe-event-url" 
            	 href="http://creativity.claremont.edu/event/upcycling-old-books/" 
            	 title="Upcycling Old Books" rel="bookmark">
                Upcycling Old Books     
            </a>
        </h2>
        <div class='av-tribe-events-outer-content-wrap'>
            <!-- Event Meta -->
            <div class="tribe-events-event-meta">
                <div class="tribe-event-schedule-details">
                    <span class="tribe-event-date-start">
                        September 22 @ 7:00 pm
                    </span> 
                    - 
                    <span class="tribe-event-time">
                        8:00 pm
                    </span>      
                </div>
            </div>
        </div>
    </div> 
   */
  val EVENT_CLASS = ".av-tribe-events-content-wrap"
  val EVENT_STARTDATE_CLASS = ".tribe-event-date-start"
  val EVENT_ENDDATE_CLASS = ".tribe-event-time"
  val EVENT_DESCRIPTION_CLASS = ".tribe-events-content"

  /**
   * Given an event, extract its title, start time, end time, and description
   */
  def processEvent(event: Element) = {
    val title = event >> text("h2")
    val start = event >> text(EVENT_STARTDATE_CLASS) 
    val end = event >> text(EVENT_ENDDATE_CLASS)
    val description = event >> element(EVENT_DESCRIPTION_CLASS) >> text("p")
    (title, start, end, description)
  }
  
  // Grab the HTML source from the Hive website
  val browser = JsoupBrowser()
  val doc = browser.get(HIVE_URL)
  
  // Grab all the events
  val events = doc >> elements(EVENT_CLASS)
  
  // Process each event and print some information
  events foreach ( event ⇒ {
    val (title, start, end, description) = processEvent(event)
    println(s"\n$title [$start]\n$description")
  })
}
