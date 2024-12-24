/*
  Create a utility function findById that takes an array of User objects and a number as arguments and returns a User | undefined (if the user with the given ID exists).
  Extend the function to support a new parameter, which specifies if the returned user should include undefined or throw an error. Use TypeScript's never type to represent the error-throwing case.
  Test the function using a sample array of User objects and handle both scenarios (with and without throwing an error).

  Export the function findById so that it can be used in the test file.
*/

import { User } from './q1';

// Utility function to find a user by ID, with an option to throw an error
export function findById(users: User[], id: number, throwOnError: boolean = false): User | undefined | never {
  const user = users.find(u => u.id === id);
  if (!user && throwOnError) {
    throw new Error(`User with ID ${id} not found`);
  }
  return user;
}
