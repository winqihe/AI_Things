/**
 * Greeting Skill
 * Handles simple greetings like "hello", "hi", "good morning"
 */

export const greetingSkill = {
  name: 'greeting',
  description: 'Handle greetings like hello, hi, good morning',

  canHandle(input) {
    const patterns = ['hello', 'hi', 'hey', 'good morning', 'good afternoon', 'good evening'];
    const lowerInput = input.toLowerCase();
    return patterns.some(pattern => lowerInput.startsWith(pattern));
  },

  async execute(input) {
    const responses = [
      'Hello! How can I help you today?',
      'Hi there! What would you like to do?',
      'Hey! Nice to see you!',
    ];
    const randomResponse = responses[Math.floor(Math.random() * responses.length)];
    return randomResponse;
  }
};
