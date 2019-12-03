package it.unipr.fava_pellegrini;

/**
 * Request Enumeration
 * It can assume 5 different type of possible requests.
 * This enumeration represents the commands requested by the clients, they are:
 *
 * Login, AddEmployee, UpdateEmployee Research, CloseConnection
 *
 * @author Daniele Pellegrini <daniele.pellegrini@studenti.unipr.it> - 285240
 * @author Riccardo Fava <riccardo.fava@studenti.unipr.it> - 287516
 */
public enum RequestList {
    RequestLogin, RequestAddEmployee, RequestUpdateEmployee, RequestResearch, Request;
}
