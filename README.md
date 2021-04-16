# Password-manager
A console-based password manager							<br>
The program is written in such way that you could easy add more encryptions.		<br>
Everything is converted into a 16-bit character array that is easy to calculate with.	<br>

<img src="pic.png" alt="Example picture"/>

## Implement encryptions
### Sinus
- Count and calculate all the chars together
- Make the Math.sin of the total
- encodes each character with one decimal place of the sine

### Prime numbers encryption
- Computes 2 to the power of 16 prime numbers to create a table
- This table contains a prime number and an index
- Each char of the password corresponds to a number. This number is the index of the table.
- Now all selected prime numbers are written one after the other in a string.
- Now the string is broken down into 16-bit parts, each part is the summant of a char.