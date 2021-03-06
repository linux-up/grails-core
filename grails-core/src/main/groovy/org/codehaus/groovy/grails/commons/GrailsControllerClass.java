/*
 * Copyright 2004-2005 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.codehaus.groovy.grails.commons;

import groovy.lang.Closure;
import groovy.lang.GroovyObject;

import java.util.Map;
import java.util.Set;

/**
 * Represents a controller class in Grails.
 *
 * @author Steven Devijver
 */
public interface GrailsControllerClass extends InjectableGrailsClass {

    /**
     * The name of the index action.
     */
    String INDEX_ACTION = "index";

    /**
     * The name of the before interceptor property.
     */
    String BEFORE_INTERCEPTOR = "beforeInterceptor";

    /**
     * The name of the after interceptor property.
     */
    String AFTER_INTERCEPTOR = "afterInterceptor";

    /**
     * The general name to use when referring to controller artefacts.
     */
    String CONTROLLER = "controller";

    /**
     * The general name to use when referring to action artefacts.
     */
    String ACTION = "action";

    /**
     * The general name to use when referring to action view.
     */
    String VIEW = "view";

    /**
     * The name of the namespace property
     */
    String NAMESPACE_PROPERTY = "namespace";


    /**
     * Checks to see if an action is accessible via a particular http method.
     *
     * @param controller The instance of the controller
     * @param httpMethod The http request method
     * @param actionName The action to check
     * @return true if the action is accessible via the specified http method
     */
    boolean isHttpMethodAllowedForAction(GroovyObject controller, String httpMethod, String actionName);

    /**
     * Checks whether the specified action is intercepted for the
     * specified controller instance.
     *
     * @param controller The instance of the controller
     * @param action The action to check
     * @return true if it is intercepted
     */
    boolean isInterceptedBefore(GroovyObject controller, String action);

    /**
     * Checks whether the specified action is intercepted after for the specified
     * controller instance.
     *
     * @param controller The controller instance
     * @param action The action to check
     * @return true if it is intercepted
     */
    boolean isInterceptedAfter(GroovyObject controller, String action);

    /**
     * Retrieves the before interceptor for the specified controller instance.
     *
     * @param controller The controller instance
     * @return The before interceptor as a Closure or null if non exists
     */
    @SuppressWarnings("rawtypes")
    Closure getBeforeInterceptor(GroovyObject controller);

    /**
     * Retrieves the after interceptor for the specified controller instance.
     *
     * @param controller The controller instance
     * @return The after interceptor as a Closure or null if non exists
     */
    @SuppressWarnings("rawtypes")
    Closure getAfterInterceptor(GroovyObject controller);

    /**
     * Gets the list of all possible URI's available in this controller.
     *
     * @return list of all possible URI's
     */
    String[] getURIs();

    /**
     * Tests if a controller maps to a given URI.
     *
     * @return true if controller maps to URI
     */
    boolean mapsToURI(String uri);

    /**
     * Retrieves the view name for the specified URI.
     *
     * @param uri the name of URI
     * @return the view name of null if not found
     */
    String getViewByURI(String uri);

    /**
     * Retrieves the view name for the specified closure name.
     *
     * @param closureName The name of the closure
     * @return The view for the specified closure action
     */
    String getViewByName(String closureName);

    /**
     * @return the namespace of this controller, null if none was specified
     */
    String getNamespace();

    /**
     * Returns a closure property name for a specific URI or null if the URI does not map to a closure.
     *
     * @param uri the URI of the request
     * @return the closure property name mapped to the URI or null is no closure was found
     */
    String getMethodActionName(String uri);

    /**
     * @deprecated This method is deprecated and will be removed in a future version of Grails
     * @return A Set of names of actions with command objects presented in this controller
     */
    @Deprecated
    @SuppressWarnings("rawtypes")
    Set getCommandObjectActions();

    /**
     * @deprecated This method is deprecated and will be removed in a future version of Grails
     * @return command object classes used by this controller
     */
    @Deprecated
    @SuppressWarnings("rawtypes")
    Set getCommandObjectClasses();

    /**
     * <p>Returns a map of the flows for this controller. A flow is an action that ends with the convention "Flow".
     *    The keys in the map are the flow ids which are the text before the "Flow" suffix. For example a flow called
     *    "bookFlow" would have a key of "book"
     * <p>The values within the Map are Groovy closures (@see groovy.lang.Closure) which represent the flow definition
     *
     * @return A Map of flows for this controller
     */
    @SuppressWarnings("rawtypes")
    Map getFlows();

    /**
     * Returns true if the given action name is a flow action.
     *
     * @param actionName The name of the action
     * @return true if it is a flow action
     */
    boolean isFlowAction(String actionName);

    /**
     * Returns the default action for this Controller.
     *
     * @return The default action
     */
    String getDefaultAction();

    /**
     * Registers a new mapping onto this controller for the given actionName.
     * @param actionName The actionName
     */
    void registerMapping(String actionName);

    /**
     * Sets the name of the default action.
     * @param defaultActionName The default action name
     */
    void setDefaultActionName(String defaultActionName);

    /**
     * Initialize the controller class
     */
    void initialize();
}
