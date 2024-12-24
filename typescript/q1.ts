/*
  Define a type alias User with the following properties:
  id (number)
  name (string)
  email (string)
  role (string | undefined)

  Create an interface Admin that extends User and adds an additional property:
  permissions (array of strings)

  Write a generic function getProperty<T, K extends keyof T>(obj: T, key: K): T[K] that retrieves a property value of a given object by its key. 
  Use the keyof operator to ensure type safety.

  Make sure to export the User type alias, Admin interface, and getProperty function by adding the export keyword in front of them.
  So that the code can be tested in the test file.
*/

// Define the User type alias with the required properties
export type User = {
  id: number;
  name: string;
  email: string;
  role?: string;
};

// Define the Admin interface that extends the User type
export interface Admin extends User {
  permissions: string[];
}

// Define the getProperty function that retrieves a property value of a given object by its key
export function getProperty<T, K extends keyof T>(obj: T, key: K): T[K] {
  return obj[key];
}
