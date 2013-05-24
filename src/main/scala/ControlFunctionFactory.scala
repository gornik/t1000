package scalatron.botwar.botPlugin

import t1000.{Logger, BotContext, Bot}
import t1000.protocol.{RequestParser, ResponseGenerator}


class ControlFunctionFactory {
  def create = (inputString: String) => {
    try
    {
      val inputCommand = RequestParser.parse(inputString)
      val ctx = BotContext(inputCommand)
      val actions = Bot.react(ctx)
      val result = ResponseGenerator.createResponseFrom(actions)
      if (!ctx.isEmpty)
        Logger.log(ctx.get.current.view.toString)
      Logger.log(result)
      result
    } catch {
      case e: Exception => {
        Logger.log(inputString)
        Logger.log(e)
      }
    }
  }
}
