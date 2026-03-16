import { greetingSkill } from './greeting.js';
import { timeSkill } from './time.js';
import { calculateSkill } from './calculate.js';

/**
 * Central registry for all available skills
 */
export const skillRegistry = new Map([
  ['greeting', greetingSkill],
  ['time', timeSkill],
  ['calculate', calculateSkill],
]);

// You can easily add new skills here:
// import { myNewSkill } from './my-new-skill.js';
// skillRegistry.set('my-new-skill', myNewSkill);
