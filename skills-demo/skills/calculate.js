/**
 * Calculate Skill
 * Handles simple arithmetic: 2 + 2, 5 * 3, etc.
 */

export const calculateSkill = {
  name: 'calculate',
  description: 'Perform simple arithmetic calculations',

  canHandle(input) {
    // Check if input contains numbers and operators
    const mathPattern = /\d+\s*[\+\-\*\/]\s*\d+/;
    return mathPattern.test(input);
  },

  async execute(input) {
    try {
      // Extract the mathematical expression
      const mathMatch = input.match(/[\d\s\+\-\*\/\(\)\.]+/);
      if (!mathMatch) {
        return 'I couldn\'t find a valid calculation.';
      }

      // Safe evaluation using Function (basic protection)
      const expression = mathMatch[0];
      const result = Function(`"use strict"; return (${expression})`)();

      return `${expression} = ${result}`;
    } catch (error) {
      return 'Sorry, I couldn\'t calculate that. Please check your expression.';
    }
  }
};
