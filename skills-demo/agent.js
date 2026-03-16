/**
 * Simple Skills Agent
 * A minimal agent that routes requests to registered skills
 */

export class Agent {
  constructor({ skills = new Map() } = {}) {
    this.skills = skills;
  }

  /**
   * Process a user request and route to appropriate skill
   */
  async process(input) {
    // Find matching skill
    const skillName = this.findSkill(input);

    if (!skillName) {
      return `I couldn't find a skill to handle: "${input}". Try "help" to see available skills.`;
    }

    const skill = this.skills.get(skillName);
    return await skill.execute(input);
  }

  /**
   * Find the first skill that can handle the input
   */
  findSkill(input) {
    for (const [name, skill] of this.skills) {
      if (skill.canHandle(input)) {
        return name;
      }
    }
    return null;
  }

  /**
   * List all available skill names
   */
  listSkills() {
    return Array.from(this.skills.keys());
  }
}
