/**
 * Time Skill
 * Handles time-related queries
 */

export const timeSkill = {
  name: 'time',
  description: 'Show current date and time',

  canHandle(input) {
    const patterns = ['time', 'date', 'what time', 'what day', 'clock'];
    const lowerInput = input.toLowerCase();
    return patterns.some(pattern => lowerInput.includes(pattern));
  },

  async execute(input) {
    const now = new Date();
    const options = {
      weekday: 'long',
      year: 'numeric',
      month: 'long',
      day: 'numeric',
      hour: '2-digit',
      minute: '2-digit',
      second: '2-digit',
      hour12: false
    };
    return `Current date and time: ${now.toLocaleString('en-US', options)}`;
  }
};
