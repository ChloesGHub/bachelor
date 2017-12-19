/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xapi.translator.fromCSV;

import com.rusticisoftware.tincan.*;
import java.net.URISyntaxException;

/**
 *
 * @author chloe
 */
public class EventMapping {
    private MoodleEvents event;
    private Statement st;
    
    public Statement generateContent(MoodleEvents event, Statement st) {
        this.event = event;
        this.st = st;
        
        switch (event.getEventname()) {
            case "\\core\\event\\course_viewed" :
                course_viewed();
                break;
            case "\\core\\event\\user_loggedin" :
                user_loggedin();
                break;
            case "\\core\\event\\user_loggedout" :
                user_loggedout();
                break;
            case "\\core\\event\\user_updated" :
                user_updated();
                break;
            case "\\core\\event\\user_password_updated" :
                user_password_updated();
                break;
            case "\\core\\event\\user_enrolment_created" :
                user_enrolment_created();
                break;
            case "\\core\\event\\user_graded" :
                user_graded();
                break;
            case "\\core\\event\\grade_deleted" :
                grade_deleted();
                break;
            case "\\core\\event\\user_list_viewed" :
                user_list_viewed();
                break;
            case "\\core\\event\\user_profile_viewed" :
                user_profile_viewed();
                break;
            case "\\core\\event\\course_created" :
                course_created();
                break;
            case "\\core\\event\\course_module_created" :
                course_module_created();
                break;
            case "\\core\\event\\message_viewed" :
                message_viewed();
                break;
            case "\\core\\event\\role_assigned" :
                role_assigned();
                break;
            case "\\core\\event\\course_section_updated" :
                course_section_updated();
                break;
            case "\\core\\event\\course_module_updated" :
                course_module_updated();
                break;
            case "\\mod_folder\\event\\course_module_viewed" :
                course_module_viewed();
                break;
            case "\\mod_scorm\\event\\course_module_viewed" :
                course_module_viewed();
                break;
            case "\\mod_assign\\event\\submission_form_viewed" :
                submission_form_viewed();
                break;
            case "\\mod_url\\event\\course_module_viewed" :
                course_module_viewed();
                break;
            case "\\mod_forum\\event\\course_module_viewed" :
                course_module_viewed();
                break;
            case "\\mod_resource\\event\\course_module_viewed" :
                course_module_viewed();
                break;
            case "\\mod_assign\\event\\submission_status_viewed" :
                submission_status_viewed();
                break;
            case "\\mod_scorm\\event\\sco_launched" :
                sco_launched();
                break;
            case "\\mod_scorm\\event\\report_viewed" :
                report_viewed();
                break;
            case "\\mod_forum\\event\\discussion_viewed" :
                discussion_viewed();
                break;
            case "\\mod_assign\\event\\assessable_submitted" :
                assessable_submitted();
                break;
            case "\\mod_page\\event\\course_module_viewed" :
                course_module_viewed();
                break;
            default:
                st = null;
                break;
        }
        return st;
    }
    
    private Verb createVerb(String name, String id) {
        Verb verb = new Verb();
        LanguageMap lmap = new LanguageMap();
        try {
            lmap.put("en", name);
            verb.setId(id);
            verb.setDisplay(lmap);
        } catch (URISyntaxException ex) {
            System.err.printf("Event %s von user %s konnte nicht konvertiert werden (Verb-Erzeugung)", event.getEventname(), event.getUserid());
        }
        return verb;
    }
    
    private Activity createObject(String description, String actId, String type) {
        LanguageMap actDescription = new LanguageMap();
        ActivityDefinition actDef = new ActivityDefinition();
        Activity activity = new Activity();
        try {
            actDescription.put("en", description);
            actDef.setDescription(actDescription);
            actDef.setType(type);
            activity.setId(actId);
            activity.setDefinition(actDef);
        } catch (URISyntaxException ex) {
            System.err.printf("Event %s von user %s konnte nicht konvertiert werden (Object-Erzeugung)", event.getEventname(), event.getUserid());
        }
        return activity;
    }
    
    private void course_viewed() {
        String verbName, verbID, objectDesc;
        verbName = "viewed";
        verbID = "http://id.tincanapi.com/verb/viewed";
        objectDesc = "A Moodle Course";
        
        st.setObject(createObject(objectDesc, event.getContextinstanceid(), event.getTarget()));
        st.setVerb(createVerb(verbName, verbID));
    }
    
    private void course_module_viewed() {
        String verbName, verbID, objectDesc;
        verbName = "viewed";
        verbID = "http://id.tincanapi.com/verb/viewed";
        objectDesc = "Moodle course-module";
        
        st.setObject(createObject(objectDesc, event.getContextinstanceid(), event.getObjecttable()));
        st.setVerb(createVerb(verbName, verbID));
    }
    
    private void user_loggedin() {
        String verbName, verbID, objectDesc;
        verbName = "loggedin";
        verbID = "https://brindlewaye.com/xAPITerms/verbs/loggedin";
        objectDesc = "moodle";
        
        st.setObject(createObject(objectDesc, event.getContextinstanceid(), event.getObjecttable()));
        st.setVerb(createVerb(verbName, verbID));
    }
    
    private void user_loggedout() {
        String verbName, verbID, objectDesc;
        verbName = "loggedout";
        verbID = "https://brindlewaye.com/xAPITerms/verbs/loggedout";
        objectDesc = "moodle";
        
        st.setObject(createObject(objectDesc, event.getContextinstanceid(), event.getObjecttable()));
        st.setVerb(createVerb(verbName, verbID));
    }
    
    private void sco_launched() {
        String verbName, verbID, objectDesc;
        verbName = "launched";
        verbID = "http://adlnet.gov/expapi/verbs/launched";
        objectDesc = "Scorm module";
        
        st.setObject(createObject(objectDesc, event.getContextinstanceid(), event.getTarget()));
        st.setVerb(createVerb(verbName, verbID));
    }
    
    private void discussion_viewed() {
        String verbName, verbID, objectDesc;
        verbName = "viewed";
        verbID = "http://id.tincanapi.com/verb/viewed";
        objectDesc = "forum discussion";
        
        st.setObject(createObject(objectDesc, event.getObjectid(), event.getTarget()));
        st.setVerb(createVerb(verbName, verbID));
    }
    
    private void report_viewed() {
        String verbName, verbID, objectDesc;
        verbName = "viewed";
        verbID = "http://id.tincanapi.com/verb/viewed";
        objectDesc = "scorm report";
        
        st.setObject(createObject(objectDesc, event.getContextinstanceid(), event.getTarget()));
        st.setVerb(createVerb(verbName, verbID));
    }
    
    private void assessable_submitted() {
        String verbName, verbID, objectDesc;
        verbName = "submitted";
        verbID = "http://activitystrea.ms/schema/1.0/submit";
        objectDesc = "forum discussion";
        
        st.setObject(createObject(objectDesc, event.getContextinstanceid(), event.getObjecttable()));
        st.setVerb(createVerb(verbName, verbID));
    }
    
    private void submission_form_viewed() {
        String verbName, verbID, objectDesc;
        verbName = "viewed";
        verbID = "http://id.tincanapi.com/verb/viewed";
        objectDesc = "submission form";
        
        st.setObject(createObject(objectDesc, event.getContextinstanceid(), event.getTarget()));
        st.setVerb(createVerb(verbName, verbID));
    }
    
    private void submission_status_viewed() {
        String verbName, verbID, objectDesc;
        verbName = "viewed";
        verbID = "http://id.tincanapi.com/verb/viewed";
        objectDesc = "submission status";
        
        st.setObject(createObject(objectDesc, event.getContextinstanceid(), event.getTarget()));
        st.setVerb(createVerb(verbName, verbID));
    }
    
    private void user_updated() {
        String verbName, verbID, objectDesc;
        verbName = "updated";
        verbID = "http://activitystrea.ms/schema/1.0/update";
        objectDesc = "user";
        
        st.setObject(createObject(objectDesc, event.getContextinstanceid(), event.getTarget()));
        st.setVerb(createVerb(verbName, verbID));
    }
    
    private void user_password_updated() {
        String verbName, verbID, objectDesc;
        verbName = "updated";
        verbID = "http://activitystrea.ms/schema/1.0/update";
        objectDesc = "user password";
        
        st.setObject(createObject(objectDesc, event.getContextinstanceid(), event.getTarget()));
        st.setVerb(createVerb(verbName, verbID));
    }
    
    private void user_enrolment_created() {
        String verbName, verbID, objectDesc;
        verbName = "created";
        verbID = "http://activitystrea.ms/schema/1.0/create";
        objectDesc = "user enrolment";
        
        st.setObject(createObject(objectDesc, event.getContextinstanceid(), event.getTarget()));
        st.setVerb(createVerb(verbName, verbID));
    }
    
    private void user_graded() {
        String verbName, verbID, objectDesc;
        verbName = "graded";
        verbID = "http://activitystrea.ms/schema/1.0/update";
        objectDesc = "user";
        
        st.setObject(createObject(objectDesc, event.getContextinstanceid(), event.getTarget()));
        st.setVerb(createVerb(verbName, verbID));
    }
    
    private void role_assigned() {
        String verbName, verbID, objectDesc;
        verbName = "assigned";
        verbID = "http://activitystrea.ms/schema/1.0/assign";
        objectDesc = "role";
        
        st.setObject(createObject(objectDesc, event.getContextinstanceid(), event.getTarget()));
        st.setVerb(createVerb(verbName, verbID));
    }
    
    private void message_viewed() {
        String verbName, verbID, objectDesc;
        verbName = "viewed";
        verbID = "http://id.tincanapi.com/verb/viewed";
        objectDesc = "message";
        
        st.setObject(createObject(objectDesc, event.getContextinstanceid(), event.getTarget()));
        st.setVerb(createVerb(verbName, verbID));
    }
    
    private void user_profile_viewed() {
        String verbName, verbID, objectDesc;
        verbName = "viewed";
        verbID = "http://id.tincanapi.com/verb/viewed";
        objectDesc = "user";
        
        st.setObject(createObject(objectDesc, event.getContextinstanceid(), event.getTarget()));
        st.setVerb(createVerb(verbName, verbID));
    }
    
    private void user_list_viewed() {
        String verbName, verbID, objectDesc;
        verbName = "viewed";
        verbID = "http://id.tincanapi.com/verb/viewed";
        objectDesc = "user profile";
        
        st.setObject(createObject(objectDesc, event.getContextinstanceid(), event.getTarget()));
        st.setVerb(createVerb(verbName, verbID));
    }
    
    private void grade_deleted() {
        String verbName, verbID, objectDesc;
        verbName = "deleted";
        verbID = "http://activitystrea.ms/schema/1.0/delete";
        objectDesc = "grade";
        
        st.setObject(createObject(objectDesc, event.getContextinstanceid(), event.getTarget()));
        st.setVerb(createVerb(verbName, verbID));
    }
    
    private void course_created() {
        String verbName, verbID, objectDesc;
        verbName = "created";
        verbID = "http://activitystrea.ms/schema/1.0/create";
        objectDesc = "moodle course";
        
        st.setObject(createObject(objectDesc, event.getContextinstanceid(), event.getTarget()));
        st.setVerb(createVerb(verbName, verbID));
    }
    
    private void course_module_created() {
        String verbName, verbID, objectDesc;
        verbName = "created";
        verbID = "http://activitystrea.ms/schema/1.0/create";
        objectDesc = "moodle course module";
        
        st.setObject(createObject(objectDesc, event.getContextinstanceid(), event.getTarget()));
        st.setVerb(createVerb(verbName, verbID));
    }
    
    private void course_section_updated() {
        String verbName, verbID, objectDesc;
        verbName = "updated";
        verbID = "http://activitystrea.ms/schema/1.0/update";
        objectDesc = "course section";
        
        st.setObject(createObject(objectDesc, event.getContextinstanceid(), event.getTarget()));
        st.setVerb(createVerb(verbName, verbID));
    }
    
    private void course_module_updated() {
        String verbName, verbID, objectDesc;
        verbName = "updated";
        verbID = "http://activitystrea.ms/schema/1.0/update";
        objectDesc = "course module";
        
        st.setObject(createObject(objectDesc, event.getContextinstanceid(), event.getTarget()));
        st.setVerb(createVerb(verbName, verbID));
    }
}
