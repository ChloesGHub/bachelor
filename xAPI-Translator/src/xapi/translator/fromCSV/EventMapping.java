/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xapi.translator.fromCSV;

/**
 *
 * @author chloe
 */

import java.util.HashMap;
import java.util.Map;
import xapi.translator.maps.XAPIStatement;

public class EventMapping extends XAPIStatement {
    public XAPIStatement generateContent(MoodleEvents event, XAPIStatement st) {
        switch (event.getEventname()) {
            case "\\core\\event\\course_viewed": {
                st.setObject(this.course_viewed(event));
                break;
            }
            case "\\core\\event\\user_loggedin": {
                st.setObject(this.user_loggedin(event));
                break;
            }
            case "\\core\\event\\user_loggedout": {
                st.setObject(this.user_loggedout(event));
                break;
            }
            case "\\core\\event\\user_updated": {
                st.setObject(this.user_updated(event));
                break;
            }
            case "\\core\\event\\user_password_updated": {
                st.setObject(this.user_password_updated(event));
                break;
            }
            case "\\core\\event\\course_created": {
                st.setObject(this.course_created(event));
                break;
            }
            case "\\core\\event\\course_module_created": {
                st.setObject(this.course_module_created(event));
                break;
            }
            case "\\core\\event\\course_section_updated": {
                st.setObject(this.course_section_updated(event));
                break;
            }
            case "\\core\\event\\course_module_updated": {
                st.setObject(this.course_module_updated(event));
                break;
            }
            case "\\mod_folder\\event\\course_module_viewed": {
                st.setObject(this.course_module_viewed(event));
                break;
            }
            case "\\mod_scorm\\event\\course_module_viewed": {
                st.setObject(this.course_module_viewed(event));
                break;
            }
            case "\\mod_assign\\event\\submission_form_viewed": {
                st.setObject(this.submission_form_viewed(event));
                break;
            }
            case "\\mod_url\\event\\course_module_viewed": {
                st.setObject(this.course_module_viewed(event));
                break;
            }
            case "\\mod_forum\\event\\course_module_viewed": {
                st.setObject(this.course_module_viewed(event));
                break;
            }
            case "\\mod_resource\\event\\course_module_viewed": {
                st.setObject(this.course_module_viewed(event));
                break;
            }
            case "\\mod_assign\\event\\submission_status_viewed": {
                st.setObject(this.submission_status_viewed(event));
                break;
            }
            case "\\mod_scorm\\event\\sco_launched": {
                st.setObject(this.sco_launched(event));
                break;
            }
            case "\\mod_scorm\\event\\report_viewed": {
                st.setObject(this.report_viewed(event));
                break;
            }
            case "\\mod_forum\\event\\discussion_viewed": {
                st.setObject(this.discussion_viewed(event));
                break;
            }
            case "\\mod_assign\\event\\assessable_submitted": {
                st.setObject(this.assessable_submitted(event));
                break;
            }
            case "\\mod_page\\event\\course_module_viewed": {
                st.setObject(this.course_module_viewed(event));
                break;
            }
            default: {
                st = null;
            }
        }
        return st;
    }

    private Map course_viewed(MoodleEvents event) {
        HashMap<String, String> object = new HashMap<>();
        String type = "Activity";
        String id = "course_id_" + event.getContextinstanceid();
        object.put("objectType", type);
        object.put("id", id);
        return object;
    }

    private Map course_module_viewed(MoodleEvents event) {
        HashMap<String, String> object = new HashMap<>();
        String type = "Activity";
        String id = "course_module_id_" + event.getContextinstanceid();
        object.put("objectType", type);
        object.put("id", id);
        return object;
    }

    private Map user_loggedin(MoodleEvents event) {
        HashMap<String, String> object = new HashMap<>();
        String type = "Agent";
        String name = event.getUsername();
        object.put("objectType", type);
        object.put("name", name);
        return object;
    }

    private Map user_loggedout(MoodleEvents event) {
        HashMap<String, String> object = new HashMap<>();
        String type = "Agent";
        String name = event.getUsername();
        object.put("objectType", type);
        object.put("name", name);
        return object;
    }

    private Map sco_launched(MoodleEvents event) {
        HashMap<String, String> object = new HashMap<>();
        String type = "Activity";
        String id = "scorm_module_id_" + event.getContextinstanceid();
        object.put("objectType", type);
        object.put("id", id);
        return object;
    }

    private Map discussion_viewed(MoodleEvents event) {
        HashMap<String, String> object = new HashMap<>();
        String type = "Activity";
        String id = "forum_discussion_id" + event.getContextinstanceid();
        object.put("objectType", type);
        object.put("id", id);
        return object;
    }

    private Map report_viewed(MoodleEvents event) {
        HashMap<String, String> object = new HashMap<>();
        String type = "Activity";
        String id = "report_id" + event.getContextinstanceid();
        object.put("objectType", type);
        object.put("id", id);
        return object;
    }

    private Map assessable_submitted(MoodleEvents event) {
        HashMap<String, String> object = new HashMap<>();
        String type = "Activity";
        String id = "module" + event.getContextinstanceid();
        object.put("objectType", type);
        object.put("id", id);
        return object;
    }

    private Map submission_form_viewed(MoodleEvents event) {
        HashMap<String, String> object = new HashMap<>();
        String type = "Activity";
        String id = "submission_form_" + event.getContextinstanceid();
        object.put("objectType", type);
        object.put("id", id);
        return object;
    }

    private Map submission_status_viewed(MoodleEvents event) {
        HashMap<String, String> object = new HashMap<>();
        String type = "Activity";
        String id = "submission_status_" + event.getContextinstanceid();
        object.put("objectType", type);
        object.put("id", id);
        return object;
    }

    private Map user_updated(MoodleEvents event) {
        HashMap<String, String> object = new HashMap<>();
        String type = "Agent";
        String name = event.getUsername();
        object.put("objectType", type);
        object.put("name", name);
        return object;
    }

    private Map user_password_updated(MoodleEvents event) {
        HashMap<String, String> object = new HashMap<>();
        String type = "Activity";
        String id = "user_password";
        object.put("objectType", type);
        object.put("id", id);
        return object;
    }

    private Map course_created(MoodleEvents event) {
        HashMap<String, String> object = new HashMap<>();
        String type = "Activity";
        String id = "course_id_" + event.getContextinstanceid();
        object.put("objectType", type);
        object.put("id", id);
        return object;
    }

    private Map course_module_created(MoodleEvents event) {
        HashMap<String, String> object = new HashMap<>();
        String type = "Activity";
        String id = "course_module_id_" + event.getContextinstanceid();
        object.put("objectType", type);
        object.put("id", id);
        return object;
    }

    private Map course_section_updated(MoodleEvents event) {
        HashMap<String, String> object = new HashMap<>();
        String type = "Activity";
        String id = "course_section_id_" + event.getContextinstanceid();
        object.put("objectType", type);
        object.put("id", id);
        return object;
    }

    private Map course_module_updated(MoodleEvents event) {
        HashMap<String, String> object = new HashMap<>();
        String type = "Activity";
        String id = "course_module_id_" + event.getContextinstanceid();
        object.put("objectType", type);
        object.put("id", id);
        return object;
    }
}